Pour créer l'image Docker de le microservice **`tp8-registry`** (qui sert d'annuaire Eureka Server), la méthode la plus propre et la plus adaptée pour un environnement pédagogique consiste à utiliser un **Dockerfile multi-étape (multi-stage)** ou à s'appuyer sur le plugin Maven déjà inclus.

---

### Option 1 : La méthode standard (Fichier `Dockerfile`)

Cette méthode utilise un fichier de configuration Docker explicite. 

#### 1. Créer le fichier `Dockerfile`

À la racine de votre projet `tp8-registry` (au même niveau que le fichier `pom.xml`), créez un fichier nommé exactement **`Dockerfile`** (sans extension) et collez-y le contenu suivant :

```dockerfile
# Étape 1 : Construction du JAR avec Maven
FROM maven:3.9.9-eclipse-temurin-25 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Création de l'image finale légère
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /app/target/tp8-registry-1.0.0.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### 2. Construire l'image Docker

Ouvrez un terminal ou une invite de commandes à la racine du projet `tp8-registry` et exécutez la commande suivante :

```bash
docker build -t tp8-registry:1.0.0 .
```

*(Le point `.` à la fin est obligatoire et indique à Docker de chercher le `Dockerfile` dans le dossier courant).*

#### 3. Lancer l'annuaire Eureka avec Docker

Une fois l'image créée, vous pouvez instancier le conteneur et mapper le port d'Eureka (`8761`) sur votre machine avec la commande :

```bash
docker run -d -p 8761:8761 --name tp8-registry-container tp8-registry:1.0.0

```

---

### Option 2 : La méthode "Zéro Config" (Via Spring Boot Maven Plugin)

Puisque le fichier `pom.xml` intègre déjà le plugin officiel de Spring Boot (`spring-boot-maven-plugin`), nous pouvons déléguer la création de l'image Docker directement à Maven sans écrire le moindre fichier `Dockerfile`. Spring Boot va utiliser la technologie *Cloud Native Buildpacks* pour configurer automatiquement l'image idéale avec la bonne version de Java.

#### 1. Lancer la génération de l'image

Exécutez simplement cette commande Maven dans votre terminal à la racine du projet :

```bash
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=tp8-registry:1.0.0

```

Maven va compiler le projet, télécharger une image de base optimisée (généralement BellSoft Liberica ou Ubuntu Chiseled avec Java), et packager votre application à l'intérieur. À la fin de l'exécution, l'image `tp8-registry:1.0.0` sera directement disponible dans votre Docker local.

---

### Comment intégrer cela dans votre `docker-compose.yml` ?

Si nous souhaitons automatiser le lancement de l'annuaire au côté de MongoDB (comme vu précédemment), l'utilisation de l'**Option 1** est parfaite. Il vous suffit d'indiquer à Docker Compose de fabriquer l'image lui-même en pointant vers le dossier du projet :

```yaml
version: '3.8'

services:
  tp8-registry:
    build: ./tp8-registry # Chemin vers le dossier contenant le pom.xml et le Dockerfile
    container_name: tp8-eureka-registry
    ports:
      - "8761:8761"

  mongodb:
    image: mongo:latest
    container_name: tp8-mongo
    ports:
      - "27017:27017"

```

Avec cette configuration globale, un simple `docker-compose up --build -d` se chargera de compiler votre code Java, de concevoir l'image de l'annuaire et de tout démarrer proprement en tâche de fond !
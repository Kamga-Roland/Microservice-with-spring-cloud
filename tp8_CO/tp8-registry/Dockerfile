# Étape 1 : Compilation du code Java avec un JDK 25 officiel
FROM eclipse-temurin:25-jdk AS compilateur
WORKDIR /app

# Installation rapide de Maven dans le conteneur de build
RUN apt-get update && apt-get install -y maven

# Copie des fichiers de configuration et du code source
COPY pom.xml .
COPY src ./src

# Compilation et empaquetage du JAR (en ignorant les tests)
RUN mvn clean package -DskipTests

# Étape 2 : Image d'exécution finale légère
FROM eclipse-temurin:25-jre
WORKDIR /app

# Récupération du JAR généré
COPY --from=compilateur /app/target/tp8-registry-1.0.0.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
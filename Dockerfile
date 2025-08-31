FROM openjdk:21-jdk-slim

WORKDIR /app

# Copier les fichiers Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copier le code source
COPY src src

# Construire l'application
RUN chmod +x gradlew
RUN ./gradlew build -x test

# Exécuter l'application
EXPOSE 8080
CMD ["java", "-jar", "build/libs/chatbot-1.0.0.jar"]
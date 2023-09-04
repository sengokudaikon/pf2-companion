FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the Gradle wrapper and build files to the container
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts ./
COPY settings.gradle.kts ./
COPY gradle.properties ./
COPY detekt.yaml ./
COPY .env.prod ./.env

# Download and cache the project dependencies
RUN ./gradlew build --no-daemon

# Copy the application source code to the container
COPY core ./core

# Build the application
RUN ./gradlew buildFatJar --no-daemon

# Expose the application port
EXPOSE 8080
RUN echo "$FIREBASE_SERVICE_ACCOUNT" | base64 -d > ./core/src/main/resources/adminsdk.json
# Run the application
CMD ["java", "-jar", "build/libs/pf2companion.jar"]
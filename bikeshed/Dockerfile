# Use an official Gradle image with JDK 24 support
FROM gradle:8.14-jdk24-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle project files first to leverage Docker cache
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Download dependencies (Docker cache optimized)
RUN ./gradlew build --no-daemon || return 0

# Copy the rest of the application source
COPY . /app

# Build the application
RUN ./gradlew bootJar --no-daemon

# ----------------------------

# Use a minimal Java 24 image to run the app
FROM eclipse-temurin:24-jre

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

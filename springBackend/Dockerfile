# Use official Java 17 image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching)
COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn

# Download dependencies first (caches this layer)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the source code
COPY src ./src

# Package the app
RUN ./mvnw clean package -DskipTests

# Expose port your Spring Boot app uses
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/demoSpring.jar"]

# Stage 1: Build the application using a JDK 21 builder
FROM openjdk:21-jdk-slim as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final, smaller image using a JRE 21 runtime
FROM openjdk:21-jre-slim
WORKDIR /app


# Copy the built jar from the builder stage
COPY --from=builder /app/Something/target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
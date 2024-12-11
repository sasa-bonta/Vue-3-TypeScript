########## Back ##########

# Build stage
FROM jelastic/maven:3.9.5-openjdk-21 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven POM file
COPY backEnd/pom.xml .

# Copy the source code
COPY backEnd/src ./src

# Build the application
RUN mvn clean package -DskipTests

# Run stage
FROM jelastic/maven:3.9.5-openjdk-21

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/fancy_project-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/fabric/connection.json /app/config/connection.json
ENV FABRIC_CONNECTION=/app/config/connection.json
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.sdjar"]
FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jre

WORKDIR /app

COPY --from=build /app/target/moutis-challenge-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

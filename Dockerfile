FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x ./mvnw
RUN ./mvnw clean install package -DskipTests=true

EXPOSE 8000
ENTRYPOINT ["java","-jar","/workspace/app/target/demo-0.0.1-SNAPSHOT.jar"]

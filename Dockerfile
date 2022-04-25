FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine as build
WORKDIR /workspace/app

COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
COPY src src
RUN chmod +x ./mvnw
RUN ./mvnw clean install package

FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine
WORKDIR /workspace/app
COPY --from=build /app/target/*.jar ./

EXPOSE 8000
ENTRYPOINT ["java","-jar","/workspace/app/demo-0.0.1-SNAPSHOT.jar"]

FROM eclipse-temurin:21-jdk AS BUILD

RUN apt-get update && apt-get install -y maven

WORKDIR /opt/app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre AS RUNTIME

WORKDIR /opt/app

COPY --from=BUILD /opt/app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
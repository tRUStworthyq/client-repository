FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /opt/app
COPY settings.xml /tmp/settings.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw -s /tmp/settings.xml dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DSkipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar", "--spring.profiles.active=docker"]
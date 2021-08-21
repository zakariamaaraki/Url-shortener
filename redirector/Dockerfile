# Build stage
FROM maven:3.6.0 AS BUILD_STAGE
WORKDIR /redirector
COPY . .
RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]

# Run stage
FROM openjdk:11.0.6-jre-slim
WORKDIR /redirector
COPY --from=BUILD_STAGE /redirector/target/*.jar redirector.jar
EXPOSE 10002
ENTRYPOINT ["java", "-jar", "redirector.jar", "--spring.config.location=classpath:/application-container.yml"]

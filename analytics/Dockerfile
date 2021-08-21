# Build stage
FROM maven:3.6.0 AS BUILD_STAGE
WORKDIR /analytics
COPY . .
RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]

# Run stage
FROM openjdk:11.0.6-jre-slim
WORKDIR /analytics
COPY --from=BUILD_STAGE /analytics/target/*.jar analytics.jar
EXPOSE 10003
ENTRYPOINT ["java", "-jar", "analytics.jar", "--spring.config.location=classpath:/application-container.yml"]

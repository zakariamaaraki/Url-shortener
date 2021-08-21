# Build stage
FROM maven:3.6.0 AS BUILD_STAGE
WORKDIR /discovery
COPY . .
RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]

# Run stage
FROM openjdk:11.0.6-jre-slim
WORKDIR /discovery
COPY --from=BUILD_STAGE /discovery/target/*.jar discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery.jar", "--spring.config.location=classpath:/application-container.yml"]

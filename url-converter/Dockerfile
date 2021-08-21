# Build stage
FROM maven:3.6.0 AS BUILD_STAGE
WORKDIR /url-converter
COPY . .
RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]

# Run stage
FROM openjdk:11.0.6-jre-slim
WORKDIR /url-converter
COPY --from=BUILD_STAGE /url-converter/target/*.jar url-converter.jar
EXPOSE 10001
ENTRYPOINT ["java", "-jar", "url-converter.jar", "--spring.config.location=classpath:/application-container.yml"]

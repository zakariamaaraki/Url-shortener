# Build stage
FROM maven:3.6.0 AS BUILD_STAGE
WORKDIR /admin
COPY . .
RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]

# Run stage
FROM openjdk:11.0.6-jre-slim
WORKDIR /admin
COPY --from=BUILD_STAGE /admin/target/*.jar admin.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "admin.jar", "--spring.config.location=classpath:/application-container.yml"]

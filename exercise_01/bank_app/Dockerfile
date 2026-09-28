FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew bootJar --no-daemon

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/libs/bank_app-0.0.1-SNAPSHOT.jar"]
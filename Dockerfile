# ---------- STAGE 1: BUILD ----------
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

# ---------- STAGE 2: RUN ----------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/shortURL-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

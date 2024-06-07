FROM openjdk:17

COPY . .

RUN ./mvnw clean install -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/api-TMDB.jar"]
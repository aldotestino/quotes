FROM adoptopenjdk/openjdk15
WORKDIR /usr/app/server
COPY . .
RUN ./mvnw clean package
EXPOSE 8080
CMD ["java", "-jar", "./target/quotes-0.0.1-SNAPSHOT.jar"]
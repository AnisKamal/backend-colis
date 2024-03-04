FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM  openjdk:17.0.1-jdk-slim
COPY --from=build /target/backend-colis-0.0.1-SNAPSHOT.jar backend-colis.jar
EXPOSE 8080

# Add wait-for-it script
ADD https://github.com/vishnubob/wait-for-it/raw/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

#ENV SPRING_PROFILES_ACTIVE=testing
ENTRYPOINT ["/wait-for-it.sh", "db:5437", "--","java","-jar","backend-colis.jar"]


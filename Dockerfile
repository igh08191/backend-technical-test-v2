FROM openjdk:11-jre-slim
COPY "./target/backend-technical-test-2.0.0-SNAPSHOT.jar" "app.jar"
EXPOSE 8080 
ENTRYPOINT ["java","-jar","app.jar"]

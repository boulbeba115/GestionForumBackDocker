FROM java:8
WORKDIR /
ADD ForumServerSide-0.0.1-SNAPSHOT.jar ForumServerSide-0.0.1-SNAPSHOT.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","ForumServerSide-0.0.1-SNAPSHOT.jar"]
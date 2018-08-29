# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Alex

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/life-project-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} life-project.jar

# Run the jar file 
#ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-Dspring.server.port=$PORT","-jar","/life-project.jar"]

CMD ["java","-jar","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","/life-project.jar"]



#  heroku login
#  heroku container:login (1Qazxsw@-!)
#  heroku container:push web -a life-project
#  heroku container:release web -a life-project
#  heroku open -a life-project

#local run
#docker run -p 8080:8080 life-project/life-project:latest

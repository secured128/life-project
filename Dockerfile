# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Alex

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8090 available to the world outside this container
EXPOSE 8090

# The application's jar file
ARG JAR_FILE=build/libs/life-project-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} life-project.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/life-project.jar"]

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/life-project.jar"]


#docker run -p 8090:8090 life-project/life-project:latest


#  heroku login
#  heroku container:login
#  heroku container:push web -a life-project
#  heroku container:release web -a life-project
#  heroku open -a life-project

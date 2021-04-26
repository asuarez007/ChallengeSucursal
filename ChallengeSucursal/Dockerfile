# Start with a base image containing Java runtime
FROM java:8

# Make port 8080 available to the world outside this container
EXPOSE 8182

ADD target/demo-1.0.jar.jar demo-1.0.jar.jar

# Run the jar file
ENTRYPOINT ["java","-jar","demo-1.0.jar.jar"]
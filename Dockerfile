FROM maven:3.8.3-openjdk-17

ENV PROJECT_HOME /usr/src/todosimpleapp
ENV JAR_NAME todosimpleapp.jar

# Create destination directory
RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

# Bundle app source
COPY . .

# Package the application as a JAR file
RUN mvn clean package -DskipTests

# Move file
RUN mv $PROJECT_HOME/target/$JAR_NAME $PROJECT_HOME/

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "todosimpleapp.jar"]
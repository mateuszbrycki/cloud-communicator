## Start & test

Build the project and launch the web app with tomcat on [http://localhost:8081](http://localhost:8081):

    cd cloud-communicator
    mvn clean spring-boot:run

Running unit tests (maven-surefire-plugin):
    mvn clean test

Application is configured to work on port 8081 and it doesn't use SSL.
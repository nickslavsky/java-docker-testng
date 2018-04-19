FROM maven:3.5.3-jdk-8

WORKDIR /usr/local/app
COPY src/ .
COPY browsermob.js .
COPY pom.xml .
COPY testng.xml .

EXPOSE 9191

RUN mvn package

CMD java -cp "target/dockertest-jar-with-dependencies.jar" org.testng.TestNG testng.xml

FROM maven:3.5.3-jdk-8

WORKDIR /usr/local/app
COPY pom.xml .
RUN ["mvn", "verify", "clean", "--fail-never"]
COPY . .
COPY browsermob.js .
COPY testng.xml .

RUN mvn package
EXPOSE 9191

ENV CLOUD_TESTING_USERNAME <your username>
ENV CLOUD_TESTING_KEY <your access key>
CMD java -cp target/dockertest-jar-with-dependencies.jar org.testng.TestNG testng.xml

FROM maven:3.5.3-jdk-8

ENV SAUCE_VERSION 4.4.12

WORKDIR /usr/local/sauce-connect

RUN apt-get update -qqy \
 && apt-get install -qqy wget \
 && apt-get clean

RUN wget https://saucelabs.com/downloads/sc-$SAUCE_VERSION-linux.tar.gz -O - | tar -xz --strip 1

CMD ["pwd"]
#CMD ["/usr/local/sauce-connect/bin/sc", "--version"]

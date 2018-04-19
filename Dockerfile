FROM maven:3.5.3-jdk-8

WORKDIR /usr/local/browsermob
ENV BMP_VERSION 2.1.4
RUN wget -O browsermob-proxy.zip https://github.com/lightbody/browsermob-proxy/releases/download/browsermob-proxy-$BMP_VERSION/browsermob-proxy-$BMP_VERSION-bin.zip \
    && unzip -q browsermob-proxy.zip \
    && rm -f browsermob-proxy.zip \
    && mv browsermob-proxy-$BMP_VERSION browsermob-proxy

WORKDIR /usr/local/sauce-connect
ENV SAUCE_VERSION 4.4.12
RUN wget https://saucelabs.com/downloads/sc-$SAUCE_VERSION-linux.tar.gz -O - | tar -xz --strip 1

WORKDIR /usr/local/browsermob

EXPOSE 9000

ADD run.sh run.sh

CMD ./run.sh

#CMD /usr/local/browsermob/browsermob-proxy/bin/browsermob-proxy >/dev/null -port 9000
#CMD curl -X POST http://localhost:9000/proxy
#CMD ps aux | grep browsermob

#CMD ["/usr/local/sauce-connect/bin/sc", "-u", "benadryl", "-k", "1f5c74ff-010d-4fc8-a140-4a00cdf1b0d4"]

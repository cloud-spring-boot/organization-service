FROM openjdk:10-jre
MAINTAINER Maksym Stepanenko <stepanenkomaksi@gmail.com>

# Alpine
# RUN  apk update && apk upgrade && apk add netcat-openbsd && apk add bash && apk add tcpdump

# Debian
RUN apt-get update && apt-get install -y tcpdump netcat git gcc make

WORKDIR /usr/local/organization-service

RUN git clone https://github.com/percona/jemalloc-debian.git jemalloc
WORKDIR /usr/local/organization-service/jemalloc
RUN ./configure --enable-prof && make && make install

WORKDIR /usr/local/organization-service
RUN mkdir -p /usr/local/organization-service
RUN mkdir -p /usr/local/organization-service/pcap
ADD @project.build.finalName@.jar /usr/local/organization-service/

ADD run.sh run.sh
ADD loop.sh loop.sh
ADD memory-image.sh memory-image.sh

RUN chmod +x run.sh loop.sh memory-image.sh

CMD ./run.sh
# CMD ./loop.sh
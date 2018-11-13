FROM openjdk:10-jre
MAINTAINER Maksym Stepanenko <stepanenkomaksi@gmail.com>

# Alpine
# RUN  apk update && apk upgrade && apk add netcat-openbsd bash tcpdump apt-utils curl

# Debian
RUN apt-get update
RUN apt-get install -y tcpdump netcat git gcc make autoconf libxslt-dev xsltproc docbook-xsl graphviz gv

WORKDIR /usr/local/organization-service

RUN git clone https://github.com/jemalloc/jemalloc.git jemalloc
WORKDIR /usr/local/organization-service/jemalloc
RUN ./autogen.sh --enable-utrace --enable-prof
RUN make dist && make && make install
RUN chmod +x /usr/local/organization-service/jemalloc/bin/jeprof

WORKDIR /usr/local/organization-service
RUN mkdir -p /usr/local/organization-service
RUN mkdir -p /usr/local/organization-service/pcap
ADD @project.build.finalName@.jar /usr/local/organization-service/

ADD run.sh run.sh
ADD loop.sh loop.sh
ADD memory-image.sh memory-image.sh

RUN chmod +x run.sh loop.sh memory-image.sh

HEALTHCHECK --start-period=30s --interval=5s CMD curl http://localhost:8080/health || exit 1

CMD ./run.sh
# CMD ./loop.sh
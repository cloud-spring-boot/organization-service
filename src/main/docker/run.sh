#!/bin/bash
echo "********************************************************"
echo "Waiting for the Eureka to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eureka $EUREKASERVER_PORT `; do sleep 3; done
echo ">>>>>>>>>>>> Eureka has started"

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z config-service $CONFIGSERVER_PORT `; do sleep 3; done
echo ">>>>>>>>>>>> Configuration Server has started"

echo "********************************************************"
echo "Waiting for the kafka server to start on port  $KAFKA_SERVER_PORT"
echo "********************************************************"
while ! `nc -z kafka $KAFKA_SERVER_PORT`; do sleep 10; done
echo "******* Kafka Server has started"

nohup tcpdump -A -s 0 'tcp port 8080 and (((ip[2:2] - ((ip[0]&0xf)<<2)) - ((tcp[12]&0xf0)>>2)) != 0)' \
-w /usr/local/organization-service/pcap/organization-$RANDOM.pcap &

echo "********************************************************"
echo "Starting organization-service "
echo "********************************************************"
java \
-Djava.security.egd=file:/dev/./urandom \
-Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI \
-Dspring.cloud.config.uri=$CONFIGSERVER_URI \
-Dspring.profiles.active=$PROFILE \
-Dspring.cloud.stream.kafka.binder.zkNodes=$KAFKA_SERVER_URI \
-Dspring.cloud.stream.kafka.binder.brokers=$ZK_SERVER_URI \
-jar /usr/local/organization-service/@project.build.finalName@.jar
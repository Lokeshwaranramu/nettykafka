# nettykafka

Problem: To create a java netty server that accepts the json object and saves it to kaska queue.

Procedure:
	 I have installed the netty server in the debian development machine. The kafka is made to run using the tar files provided by apache.

Running Kafta includes following commands in debian:
*Download the 0.10.2.0 release and un-tar it. 
*> tar -xzf kafka_2.11-0.10.2.0.tgz
*> cd kafka_2.11-0.10.2.0
*> bin/zookeeper-server-start.sh config/zookeeper.properties
*> bin/kafka-server-start.sh config/server.properties

Program given in this repository is used to get the time delay 10 seconds from the system as nanoseconds. These nanoseconds value are taken by the Netty server.
The Encode and Decoder in the Codec folder is used to transfer the time from client to server as json object.

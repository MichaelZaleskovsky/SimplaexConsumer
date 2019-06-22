# SimplaexConsumer
Test work for SIMPLAEX company.
Implement data consumer and analyzer for input stream of data on TCP port

# How to START
Open project in IDE and run TCPConsumer class

or 

Compile project with Maven
by "mvn clean instal" and run JAR file in Java without any parameters

then

start Producer in TCP mode.

result

Consumer will write result to files with names "simplaex\<timeStamp\>.txt" to the folder "./output" until data flow will stop or process wil be killed.

# JUnit test
Project contain JUnit test class DataConsumerTest.java


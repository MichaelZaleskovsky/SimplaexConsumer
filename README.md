# SimplaexConsumer
Test work for SIMPLAEX company.
Implement data consumer and analyzer for input stream of data on TCP port

# How to START
Open project in IDE and run TCPConsumer class

or 

Compile project with Maven
by "mvn clean instal" and run JAR file in Java without any parameters

then

start Producer

result

Consumer will write result to files with names "simplaex\<timeStamp\>.txt" to the folder "./output" until data flow will stop or process wil be killed.

# Manual test
For manual testing of programm use test TCPProducer at https://github.com/MichaelZaleskovsky/SimplaexProducer
To test you have to set parameter TCPConsumer.GROUP_BY to 5
After start producer you will get 2 files in output folder and 1 message that one bundle of data have incorrect data.


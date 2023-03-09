```shell
mvn package -P buildAgent
mvn package -P buildApplication
cd target
java -javaagent:instrumentation-1.0.0-SNAPSHOT-agent.jar -jar instrumentation-1.0.0-SNAPSHOT-jar-with-dependencies.jar 12 1432 12321 1312
```
## Static Load
```shell
mvn package -P buildAgent
mvn package -P buildApplication
cd target
java -javaagent:instrumentation-1.0.0-SNAPSHOT-agent.jar -jar instrumentation-1.0.0-SNAPSHOT-jar-with-dependencies.jar StartMyAtmApplication 1432 12321 1312
```

## Dynamic Load
```shell
mvn package -P buildAgent
mvn package -P buildApplication
cd target
java -jar instrumentation-1.0.0-SNAPSHOT-jar-with-dependencies.jar StartMyAtmApplication 1432 2321 1312
java -jar instrumentation-1.0.0-SNAPSHOT-jar-with-dependencies.jar LoadAgent
```

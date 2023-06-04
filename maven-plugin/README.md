https://www.baeldung.com/maven-plugin

```shell
mvn me.coconan:counter-maven-plugin:0.0.1-SNAPSHOT:dependency-counter
mvn -s .mvn/settings.xml counter:dependency-counter
mvn -s .mvn/settings.xml counter:dependency-counter -Dscope=test
```

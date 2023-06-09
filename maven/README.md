## Phase, Plugin and Goals

## References
- [FAT JAR files for testing in restricted test environments](https://daniel-delimata.medium.com/fat-jar-files-for-testing-in-restricted-test-environments-a6b1756a3f6e)
- [build profile](https://maven.apache.org/guides/introduction/introduction-to-profiles.html)
- [dependency mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- [How can I include test classes into Maven jar and execute them?](https://stackoverflow.com/questions/36047637/how-can-i-include-test-classes-into-maven-jar-and-execute-them)
- https://stackoverflow.com/questions/46489455/append-the-value-of-argline-param-in-maven-surefire-plugin
- http://www.devll.org/blog/2020/java/jacoco-argline.html
- https://stackoverflow.com/questions/52188683/running-spring-tests-from-executable-jar

## notes
- `<packaging>pom</packaging>`
- `<relativePath/>`

## commands
- `source ~/scripts/change-java-8.sh`
- `mvn clean && mvn test-compile && mvn package -Dmaven.test.skip=true`
- `java -jar target/maven-1.0.0-SNAPSHOT-fat-tests.jar`

- `mvn test -Dtest=BarTest`
- `mvn test -Dtest=BarTest#test_greet`
- `mvn test -Dtest=BarTest -DfailIfNoTests=false -pl foo-module -am`

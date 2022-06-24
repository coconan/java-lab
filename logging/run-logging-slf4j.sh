rm -rf mods/*
curl https://repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.0-alpha7/slf4j-api-2.0.0-alpha7.jar > mods/slf4j-api-2.0.0-alpha7.jar
curl https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.3.0-alpha9/logback-core-1.3.0-alpha9.jar > mods/logback-core-1.3.0-alpha9.jar
curl https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.3.0-alpha9/logback-classic-1.3.0-alpha9.jar > mods/logback-classic-1.3.0-alpha9.jar

# compile logging module
javac --module-path mods -d mods/me.coconan.logging.slf4j src/modules/me.coconan.logging.slf4j/module-info.java \
	src/modules/me.coconan.logging.slf4j/me/coconan/logging/slf4j/*.java

# compile logging main app module
javac --module-path mods -d mods/me.coconan.logging.app src/modules/me.coconan.logging.app/module-info.java \
	src/modules/me.coconan.logging.app/me/coconan/logging/app/*.java

# run logging main app
java --module-path mods -Dlogback.configurationFile=mods/logback.xml -m me.coconan.logging.app/me.coconan.logging.app.MainApp


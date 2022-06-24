# compile logging module
javac --module-path mods -d mods/me.coconan.logging.java9platform src/modules/me.coconan.logging.java9platform/module-info.java \
	src/modules/me.coconan.logging.java9platform/me/coconan/logging/java9platform/*.java

# compile logging main app module
javac --module-path mods -d mods/me.coconan.logging.app src/modules/me.coconan.logging.app/module-info.java \
	src/modules/me.coconan.logging.app/me/coconan/logging/app/*.java

# run logging main app
java --module-path mods -m me.coconan.logging.app/me.coconan.logging.app.MainApp


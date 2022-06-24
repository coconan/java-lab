module me.coconan.logging.java9platform {
	provides java.lang.System.LoggerFinder
		with me.coconan.logging.java9platform.CustomLoggerFinder;
	exports me.coconan.logging.java9platform;
}

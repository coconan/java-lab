module me.coconan.logging.slf4j {
	requires org.slf4j;
	provides java.lang.System.LoggerFinder
		with me.coconan.logging.slf4j.Slf4jLoggerFinder;
	exports me.coconan.logging.slf4j;
}


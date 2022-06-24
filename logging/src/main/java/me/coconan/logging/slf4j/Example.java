package me.coconan.logging.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To switch between logging frameworks, just uncomment needed framework dependencies in pom.xml
 */
public class Example {
	
	private static Logger logger = LoggerFactory.getLogger(Example.class);

	public static void main(String[] args) {
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.error("Error log message");

		String variable = "Hello Coconan";
		logger.debug("Printing variable value {}", variable);
	}
}

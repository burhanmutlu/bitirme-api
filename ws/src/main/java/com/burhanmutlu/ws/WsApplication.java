package com.burhanmutlu.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

	private static final Logger logger = LoggerFactory.getLogger(WsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.warn("Warning log message");
		logger.error("Error log message");
	}

}

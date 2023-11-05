package com.burhanmutlu.ws;

<<<<<<< HEAD
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 1d9b436d83a84bcb57f070a08b6ee70a5fb85d6c
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

<<<<<<< HEAD
	private final static Logger logger = LogManager.getLogger(WsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
		logger.info("info");
		logger.debug("debug");
		logger.error("error");
		logger.warn("warn");
=======
	private static final Logger logger = LoggerFactory.getLogger(WsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.warn("Warning log message");
		logger.error("Error log message");
>>>>>>> 1d9b436d83a84bcb57f070a08b6ee70a5fb85d6c
	}

}

package com.burhanmutlu.ws;

import com.burhanmutlu.ws.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WsApplication {

	private static final Logger logger = LogManager.getLogger(WsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
		logger.info("info");
		logger.debug("debug");
		logger.error("error");
		logger.warn("warn");
	}

	CommandLineRunner createInitialUsers(UserService userService) {
		return (args -> {
			logger.info("deneme");
		});
	}

}

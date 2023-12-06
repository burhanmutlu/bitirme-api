package com.burhanmutlu.ws;

import com.burhanmutlu.ws.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsApplication {
	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}
}

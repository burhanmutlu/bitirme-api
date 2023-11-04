package com.burhanmutlu.ws;

import com.burhanmutlu.ws.entity.Account;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.repository.UserRepository;
import com.burhanmutlu.ws.service.UserService;
import com.burhanmutlu.ws.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootTest
class WsApplicationTests {
	@Test
	void contextLoads() {
	}
}

package com.test_demo.server_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import repo.ServerRepo;
import model.Server;

import static enumeration.Status.*;

@SpringBootTest
class ServerDemoApplicationTests {

	@Test
	void contextLoads(String[] args) {
		SpringApplication.run(ServerDemoApplicationTests.class, args);
	}
	
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Test1", "16GB", "Test PC", "http://localhost:8080/server/image/server1.png", SERVER_UP));
			serverRepo.save(new Server(null, "172.30.1.27", "Test2", "16GB", "Test PC", "http://localhost:8080/server/image/server2.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.125", "Test3", "16GB", "Test PC", "http://localhost:8080/server/image/server3.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.132", "Test4", "16GB", "Test PC", "http://localhost:8080/server/image/server4.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.122", "Test5", "16GB", "Test PC", "http://localhost:8080/server/image/server5.png", SERVER_UP));
		};
	}

}

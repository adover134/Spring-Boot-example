package com.test_demo.server_demo;

import static enumeration.Status.SERVER_UP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import model.Server;
import repo.ServerRepo;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repo")
@EntityScan(basePackages = {"model"})
@ComponentScan(basePackages = {"com.test_demo.server_demo", "resource", "implementation"})
public class ServerDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerDemoApplication.class, args);
  }
  
  @Bean
  CommandLineRunner run(ServerRepo serverRepo) {
	  return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Test1", "16GB", "Test PC", "http://localhost:8080/server/image/server1.png", SERVER_UP));
	  };
  }

}
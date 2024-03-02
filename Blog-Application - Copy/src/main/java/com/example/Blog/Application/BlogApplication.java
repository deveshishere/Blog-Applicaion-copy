package com.example.Blog.Application;

import com.example.Blog.Application.configuration.ServerProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@ComponentScan
@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
public class BlogApplication {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BlogApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", ServerProperties.getPropertyValue("server.port")));
		app.run(args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}


package com.example.Assignment3;

import com.example.Assignment3.Controller.AppController;
import com.example.Assignment3.Model.Model.Article;
import com.example.Assignment3.Model.Model.Writer;
import com.example.Assignment3.Model.Service.ArticleService;
import com.example.Assignment3.Model.Service.WriterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.LinkedList;

@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EntityScan
@ComponentScan
public class Assignment3Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

	@Bean
	public CommandLineRunner data(AppController controller) {
		return (args -> {
			controller.getAllWriters().forEach(w -> {
				System.out.println(w.getPassword() + " " + w.getPassword());
			});
		});
	}

}

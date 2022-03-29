package ru.learnUp.lesson23.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(HibernateApplication.class, args);


	}

}

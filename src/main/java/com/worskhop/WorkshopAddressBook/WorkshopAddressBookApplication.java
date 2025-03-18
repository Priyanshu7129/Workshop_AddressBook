package com.worskhop.WorkshopAddressBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // ✅ Enables caching in Spring Boot
public class WorkshopAddressBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopAddressBookApplication.class, args);
	}
}


//http://localhost:8080/swagger-ui.html for swagger
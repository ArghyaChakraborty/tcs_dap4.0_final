package com.tcs.dap40.emiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmiServiceApplication.class, args);
	}

}

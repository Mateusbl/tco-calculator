package com.mateus.tco_calculator;

import org.springframework.boot.SpringApplication;

public class TestTcoCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.from(TcoCalculatorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

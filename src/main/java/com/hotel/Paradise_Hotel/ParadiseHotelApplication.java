package com.hotel.Paradise_Hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hotel.Paradise_Hotel"})
public class ParadiseHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParadiseHotelApplication.class, args);
	}

}

package com.bht.dimage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bht.dimage.dao")
@SpringBootApplication
public class DimageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DimageApplication.class, args);
	}

}

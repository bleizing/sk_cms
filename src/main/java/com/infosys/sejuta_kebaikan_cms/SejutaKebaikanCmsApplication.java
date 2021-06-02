package com.infosys.sejuta_kebaikan_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SejutaKebaikanCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SejutaKebaikanCmsApplication.class, args);
	}

}

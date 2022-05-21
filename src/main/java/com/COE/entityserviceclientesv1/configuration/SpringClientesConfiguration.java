package com.COE.entityserviceclientesv1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.COE.entityserviceclientesv1.model.ClientesBD;

@Configuration
public class SpringClientesConfiguration {
	
	@Bean
	public ClientesBD clientesBD() {
		return new ClientesBD();
	}
}
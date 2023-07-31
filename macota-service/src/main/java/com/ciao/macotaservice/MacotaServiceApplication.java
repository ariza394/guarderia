package com.ciao.macotaservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.ciao.macotaservice.utilities.RestApiClient;

@SpringBootApplication
public class MacotaServiceApplication {

	@Bean
	ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

	@Bean
	@Qualifier("restApiClientImages")
	public RestApiClient restApiClientImages(WebClient.Builder webClientBuilder) {
		return RestApiClient.create(webClientBuilder, "http://localhost:8082");
	}

	@Bean
	@Qualifier("restApiClientUsers")
	public RestApiClient restApiClientUsers(WebClient.Builder webClientBuilder) {
		return RestApiClient.create(webClientBuilder, "http://localhost:8080");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MacotaServiceApplication.class, args);
	}

}

package com.ciao.macotaservice.utilities;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class RestApiClient {

    private final WebClient webClient;

    private RestApiClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    // Agregar este constructor público
    public RestApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public static RestApiClient create(WebClient.Builder webClientBuilder, String baseUrl) {
        return new RestApiClient(webClientBuilder, baseUrl);
    }

    public <T> T get(String endpoint, Class<T> responseType) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <T> T post(String endpoint, MultiValueMap<String, Object> formData, Class<T> responseType) {
        return webClient.post()
                .uri(endpoint)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(formData))
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
}
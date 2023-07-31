package com.ciao.macotaservice.utilities;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.ciao.macotaservice.dto.imagenOneDto.ImagenOneDto;

@Component
public class GetOneImage {
    

    private final RestApiClient restApiClient;

    public GetOneImage(@Qualifier("restApiClientImages") RestApiClient restApiClient) {
        this.restApiClient = restApiClient;
    }

    public ImagenOneDto imagenByID(Long id, String modelo) {
        String endpoint = "/imagenes/one/" + id + "/" + modelo;
        return restApiClient.get(endpoint, ImagenOneDto.class);
    }

    public ImagenOneDto imagenUpdateByID(String modelo, MultiValueMap<String, Object> formData) {
        String endpoint = "/imagenes/one/" + modelo;
        return restApiClient.post(endpoint, formData, ImagenOneDto.class);
    }

}
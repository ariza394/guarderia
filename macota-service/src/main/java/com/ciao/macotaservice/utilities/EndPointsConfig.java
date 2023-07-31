package com.ciao.macotaservice.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndPointsConfig {
    @Value("${points.one-image}")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}

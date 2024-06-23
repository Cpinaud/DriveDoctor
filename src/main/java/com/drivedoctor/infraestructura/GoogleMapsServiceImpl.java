package com.drivedoctor.infraestructura;

import com.drivedoctor.config.GoogleMapsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsServiceImpl {

    private final String apiKey;

    @Autowired
    public GoogleMapsServiceImpl(GoogleMapsConfig googleMapsConfig) {
        this.apiKey = googleMapsConfig.getApiKey();
    }


}

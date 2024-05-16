package com.jacobFrancois.NationalWeatherServiceWrapper.service;

import com.jacobFrancois.NationalWeatherServiceWrapper.service.client.NationalWeatherServiceClient;
import reactor.core.publisher.Mono;

public class WeatherService {

    private final NationalWeatherServiceClient weatherServiceClient;

    private WeatherService(NationalWeatherServiceClient weatherServiceClient) {
        this.weatherServiceClient = weatherServiceClient;
    }

    public Mono<String> getLocationMetadata(String latitude, String longitude) {
        // Delegate the call to fetchLocationMetaDataByLatLong to the client
        return weatherServiceClient.fetchLocationMetaDataByLatLong(latitude, longitude);
    }
}

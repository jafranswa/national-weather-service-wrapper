package com.jacobFrancois.NationalWeatherServiceWrapper.service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NationalWeatherServiceClient {

    private final WebClient webClient;
    private static final String BASE_URL = "https://api.weather.gov/";

    public NationalWeatherServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();;
    }

    public Mono<String> fetchLocationMetaDataByLatLong(String latitude, String longitude) {
        String uri = "/points/" + latitude + "," + longitude;
        return this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> fetchForecastData(String url) {
        String uri = removeBaseUrl(url);
        return this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
    }

    private String removeBaseUrl(String fullUrl) {
        // Check if the full URL starts with the base URL
        if (fullUrl.startsWith(BASE_URL)) {
            // Use substring to remove the base URL portion
            return fullUrl.substring(BASE_URL.length());
        } else {
            // If the full URL does not start with the base URL, return the original URL
            return fullUrl;
        }
    }

}


package com.jacobFrancois.NationalWeatherServiceWrapper.controller;

import com.jacobFrancois.NationalWeatherServiceWrapper.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/highlow")
    public ResponseEntity getHighLowTemperatures(
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude) {

        if (!validLatLong(latitude, longitude)) {
            return ResponseEntity.badRequest().body("Latitude and longitude are required parameters and must be numeric values.");
        }

        // carry out request
        String weatherTempForcastJson = weatherService.getHighLowTempsForecast(latitude, longitude);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(weatherTempForcastJson);
    }

    private boolean validLatLong(String latitude, String longitude) {
        // return error if request parameters are not present
        if ((latitude == null || longitude == null) || (latitude.equals("") || longitude.equals(""))) {
            return false;
        }

        // return error if request parameters are not numeric values
        double lat;
        double lon;
        try {
            lat = Double.parseDouble(latitude);
            lon = Double.parseDouble(longitude);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}

package com.jacobFrancois.NationalWeatherServiceWrapper.controller;

import com.jacobFrancois.NationalWeatherServiceWrapper.service.WeatherService;
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


//        String latitude = "39.7349";
//        String longitude = "-104.972";

        // todo replace this with a call to something that returns the highs and lows for the days
        String weather = weatherService.getHighLowTemps(latitude, longitude);

        return ResponseEntity.ok().body(weather);
    }
}

package com.jacobFrancois.NationalWeatherServiceWrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {

    @JsonProperty("forecastHourly")
    private String forecastHourly;

    public String getForecastHourly() {
        return forecastHourly;
    }

    public void setForecastHourly(String forecastHourly) {
        this.forecastHourly = forecastHourly;
    }
}

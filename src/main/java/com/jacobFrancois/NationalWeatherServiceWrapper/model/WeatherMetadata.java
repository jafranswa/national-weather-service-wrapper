package com.jacobFrancois.NationalWeatherServiceWrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherMetadata {
//    private String id;
    private Properties properties;

//    @JsonProperty("@id")
//    public String getId() {
//        return id;
//    }
//
//    @JsonProperty("@id")
//    public void setId(String id) {
//        this.id = id;
//    }

    @JsonProperty("@properties")
    public Properties getProperties() {
        return properties;
    }

    @JsonProperty("@properties")
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}

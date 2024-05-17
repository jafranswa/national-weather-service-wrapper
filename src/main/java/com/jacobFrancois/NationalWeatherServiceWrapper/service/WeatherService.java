package com.jacobFrancois.NationalWeatherServiceWrapper.service;

import org.json.JSONArray;
import org.json.JSONObject;
import com.jacobFrancois.NationalWeatherServiceWrapper.service.client.NationalWeatherServiceClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;

@Service
public class WeatherService {

    private final NationalWeatherServiceClient weatherServiceClient;

    private WeatherService(NationalWeatherServiceClient weatherServiceClient) {
        this.weatherServiceClient = weatherServiceClient;
    }

    public String getHighLowTempsForecast(String latitude, String longitude) {
        String locationMetaData = weatherServiceClient.fetchLocationMetaDataByLatLong(
                        formatLatLong(latitude),
                        formatLatLong(longitude))
                .block();

        String forcastData = fetchLocationForecast(locationMetaData).block();
        JSONObject forcastHighLowJson = buildWeeklyForecastHighLowJson(forcastData);
        return forcastHighLowJson.toString();
    }

    public String formatLatLong(String latOrLong) {
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        Double latLong = Double.parseDouble(latOrLong);
        return decimalFormat.format(latLong);
    }

    public Mono<String> fetchLocationForecast(String json) {
        String forcastUrl = getForecastUrl(json);
        Mono<String> data = weatherServiceClient.fetchForecastData(forcastUrl);
        return data;
    }

    public String getForecastUrl(String json) {
        String forecastUrl = "";
        try {
            // Parse JSON string to JSONObject
            JSONObject jsonObject = new JSONObject(json);

            // Navigate to properties and get forecast value
            JSONObject properties = jsonObject.getJSONObject("properties");
            forecastUrl = properties.getString("forecast");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forecastUrl;
    }

    public JSONObject buildWeeklyForecastHighLowJson(String forecastData) {

        // Parse the original JSON
        JSONObject originalJsonObject = new JSONObject(forecastData);

        // Extract the periods array
        JSONArray periodsArray = originalJsonObject.getJSONObject("properties").getJSONArray("periods");

        // Create a new JSON array with the desired structure
        JSONArray newPeriodsArray = new JSONArray();
        for (int i = 0; i < periodsArray.length(); i++) {
            JSONObject period = periodsArray.getJSONObject(i);
            JSONObject newPeriod = new JSONObject();
            newPeriod.put("name", period.getString("name"));
            newPeriod.put("temperature", period.getInt("temperature"));
            newPeriodsArray.put(newPeriod);
        }

        // Create the final JSON object
        JSONObject finalJsonObject = new JSONObject();
        finalJsonObject.put("periods", newPeriodsArray);

        return finalJsonObject;
    }

}

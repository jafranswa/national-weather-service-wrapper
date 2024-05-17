package com.jacobFrancois.NationalWeatherServiceWrapper.service;

import com.jacobFrancois.NationalWeatherServiceWrapper.service.client.NationalWeatherServiceClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertEquals;

@SpringBootTest
public class WeatherServiceTest {
    @Mock
    private NationalWeatherServiceClient weatherServiceClient;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFormatLatLong() {

        // Test cases to validate the method
        assertEquals(weatherService.formatLatLong("123.456789"), "123.4568");
        assertEquals(weatherService.formatLatLong("123.456000"), "123.456");
        assertEquals(weatherService.formatLatLong("123.4567000"), "123.4567");
        assertEquals(weatherService.formatLatLong("123.0000"), "123");
        assertEquals(weatherService.formatLatLong("0.0000"), "0");
        assertEquals(weatherService.formatLatLong("-123.456789"), "-123.4568");
        assertEquals(weatherService.formatLatLong("-123.456000"), "-123.456");
    }

    @Test
    public void testGetForecastUrl() {
        String json = "{ \"properties\": { \"forecast\": \"https://api.weather.gov/gridpoints/BOU/62,61/forecast\" } }";
        String expectedUrl = "https://api.weather.gov/gridpoints/BOU/62,61/forecast";
        assertEquals(weatherService.getForecastUrl(json), expectedUrl);

        // Test case with missing "forecast" property
        String jsonMissingForecast = "{ \"properties\": { \"anotherProperty\": \"someValue\" } }";
        assertEquals(weatherService.getForecastUrl(jsonMissingForecast), "");

        // Test case with missing "properties" object
        String jsonMissingProperties = "{ \"otherProperty\": \"someValue\" }";
        assertEquals(weatherService.getForecastUrl(jsonMissingProperties), "");

        // Test case with invalid JSON
        String invalidJson = "{ \"properties\": { \"forecast\": \"https://api.weather.gov/gridpoints/BOU/62,61/forecast\" ";
        assertEquals(weatherService.getForecastUrl(invalidJson), "");
    }

    @Test
    public void testBuildWeeklyForecastHighLowJson() {
        // Input JSON data with periods containing name and temperature properties
        String inputJson = "{ \"properties\": { \"periods\": [ { \"name\": \"Monday\", \"temperature\": 80 }, { \"name\": \"Tuesday\", \"temperature\": 75 } ] } }";

        // Expected JSON output
        JSONObject expectedJson = new JSONObject();
        JSONArray expectedPeriods = new JSONArray();
        JSONObject monday = new JSONObject();
        monday.put("name", "Monday");
        monday.put("temperature", 80);
        JSONObject tuesday = new JSONObject();
        tuesday.put("name", "Tuesday");
        tuesday.put("temperature", 75);
        expectedPeriods.put(monday);
        expectedPeriods.put(tuesday);
        expectedJson.put("periods", expectedPeriods);

        // Test the method with the input JSON
        JSONObject actualJson = weatherService.buildWeeklyForecastHighLowJson(inputJson);

        // Compare the expected and actual JSON
        assertEquals(actualJson.toString(), expectedJson.toString());
    }

}

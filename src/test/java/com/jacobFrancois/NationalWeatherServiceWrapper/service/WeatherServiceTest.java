package com.jacobFrancois.NationalWeatherServiceWrapper.service;

import com.jacobFrancois.NationalWeatherServiceWrapper.service.client.NationalWeatherServiceClient;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
    public void testGetLocationMetadata() {
        // Given
        String latitude = "40.73061";
        String longitude = "-73.935242";
        String expectedMetadata = "mock weather data";

        // Mock behavior of weatherServiceClient.fetchLocationMetaDataByLatLong
        when(weatherServiceClient.fetchLocationMetaDataByLatLong(latitude, longitude))
                .thenReturn(Mono.just(expectedMetadata));

        // When
        Mono<String> metadataMono = weatherService.getLocationMetadata(latitude, longitude);

        // Then
        metadataMono.subscribe(metadata -> {
            assertNotNull(metadata);
            assertEquals(metadata, expectedMetadata);
        });
    }
}

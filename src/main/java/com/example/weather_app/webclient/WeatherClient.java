package com.example.weather_app.webclient;

import com.example.weather_app.model.Forecast;
import com.example.weather_app.model.Location;
import com.example.weather_app.webclient.dto.WeatherbitDataDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {

    private static final String WEATER_URL = "http://api.weatherbit.io/v2.0/forecast/";
    private static final String API_KEY = "56c3df3771794dfab56b72bdd23e7772";
    private RestTemplate restTemplate = new RestTemplate();

    public Forecast getForecast(Location location, double day) {
        double lat= location.getLatitude();
        double lon= location.getLongitude();
        WeatherbitDataDto weatherbitDataDto = callGetMethod("daily?lat={lat}&lon={lon}&days={day}&key={apiKey}",
                WeatherbitDataDto.class,
                lat, lon, day, API_KEY);
        return Forecast.builder()
                .temperature(weatherbitDataDto.getTemp())
                .windSpeed(weatherbitDataDto.getWind_spd())
                .location(location)
                .build();
    }


    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATER_URL + url,
                responseType, objects);
    }


}

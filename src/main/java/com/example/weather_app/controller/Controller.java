package com.example.weather_app.controller;

import com.example.weather_app.model.Forecast;
import com.example.weather_app.model.Location;
import com.example.weather_app.service.WeatherbitForecastProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class Controller {

//    private final SpotProvider spotProvider;
    private final WeatherbitForecastProvider weatherbitForecastProvider;


//    @GetMapping("forecast")
//    public SurfingSpot getSurfingSpot(@RequestParam(value = "date") String date) {
//
//        return spotProvider.findBestSpot(DateUtils.getDate(date))
//                .orElseThrow(() -> new ResourceNotFoundException("No suitable spots at given date"));
//    }

    @GetMapping("/weather")
    public Optional<Forecast> getForecast() {
        LocalDate today = LocalDate.now();

        return weatherbitForecastProvider.getForecast(Location.JASTARNIA, today);
    }

}

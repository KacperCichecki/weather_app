package com.example.weather_app.controller;

import com.example.weather_app.exception.ResourceNotFoundException;
import com.example.weather_app.service.SpotProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final SpotProvider spotProvider;

    @GetMapping("forecast")
    public ResponseEntity<?> getSurfingSpot(@RequestParam(value = "date") String date) {

        try {
            return spotProvider.findBestSpot(LocalDate.parse(date))
                    .map(surfingSpot -> ResponseEntity.status(HttpStatus.OK).body(surfingSpot))
                    .orElseThrow(() -> new ResourceNotFoundException("No suitable spots at given date"));
        } catch (ResourceNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re);
        } catch (IllegalArgumentException ia) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ia.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

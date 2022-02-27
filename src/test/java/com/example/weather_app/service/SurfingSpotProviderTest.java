package com.example.weather_app.service;

import com.example.weather_app.model.Forecast;
import com.example.weather_app.model.SurfingSpot;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.weather_app.model.Location.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SurfingSpotProviderTest {

    @Mock
    private ForecastProvider forecastProvider;

    @InjectMocks
    private SurfingSpotProvider surfingSpotProvider;


    @ParameterizedTest
    @MethodSource("provideLocationsWithNoGoodWeatherConditions")
    void findBestSpot_noneWeatherConditionInRightRange_returnsEmptyOptional(List<Forecast> forecastList) {
        //given
        forecastList.forEach(forecast -> when(forecastProvider.getForecast(eq(forecast.getLocation()), any(LocalDate.class)))
                .thenReturn(Optional.of(forecast)));
        //when
        Optional<SurfingSpot> bestSpot = surfingSpotProvider.findBestSpot(LocalDate.now());
        //then
        assertTrue(bestSpot.isEmpty());
    }

    public static Stream<Arguments> provideLocationsWithNoGoodWeatherConditions() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Forecast(BRIDGETOWN, 4, 15),
                                new Forecast(JASTARNIA, 3, 23),
                                new Forecast(FORTALEZA, -23, 12),
                                new Forecast(PISSOURI, -16, 17),
                                new Forecast(LE_MORNE, 0, 5))
                ),
                Arguments.of(
                        List.of(
                                new Forecast(BRIDGETOWN, 5, -5),
                                new Forecast(JASTARNIA, 7, -23),
                                new Forecast(FORTALEZA, 13, 45),
                                new Forecast(PISSOURI, 10, 4),
                                new Forecast(LE_MORNE, 6, 36))
                ),
                Arguments.of(
                        List.of(
                                new Forecast(BRIDGETOWN, 4, -5),
                                new Forecast(JASTARNIA, -7, -23),
                                new Forecast(FORTALEZA, 50, 45),
                                new Forecast(PISSOURI, 19, 4),
                                new Forecast(LE_MORNE, 0, 36))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideLocationsWithGoodWeatherConditionsAndBestSurfingSpots")
    void findBestSpot_weatherConditionInRightRange_returnsOptionalOfBestSurfingSpot(List<Forecast> forecastList,
                                                                                    SurfingSpot expectedSurfingSpot) {
        //given
        LocalDate date = LocalDate.now().plusDays(1);
        forecastList.forEach(forecast -> when(forecastProvider.getForecast(eq(forecast.getLocation()), eq(date)))
                .thenReturn(Optional.of(forecast)));
        //when
        Optional<SurfingSpot> bestSpot = surfingSpotProvider.findBestSpot(date);
        //then
        assertEquals(bestSpot.get(), expectedSurfingSpot);
    }

    public static Stream<Arguments> provideLocationsWithGoodWeatherConditionsAndBestSurfingSpots() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Forecast(BRIDGETOWN, 5, 15),
                                new Forecast(JASTARNIA, 7, 23),
                                new Forecast(FORTALEZA, 17, 12),
                                new Forecast(PISSOURI, 13, 17),
                                new Forecast(LE_MORNE, 18, 5)),
                        new SurfingSpot(FORTALEZA, 12, 17)
                ),
                Arguments.of(
                        List.of(
                                new Forecast(BRIDGETOWN, 5, 5),
                                new Forecast(JASTARNIA, 7, 23),
                                new Forecast(FORTALEZA, 13, 3),
                                new Forecast(PISSOURI, 10, 20),
                                new Forecast(LE_MORNE, 18, 15)),
                        new SurfingSpot(LE_MORNE, 15, 18)
                )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {17, 20, 200, -1, -20, -16})
    void findBestSpot_dateOutOfRange_throwsIllegalArgumentException(int day) {
        //given
        LocalDate date = LocalDate.now().plusDays(day);
        Forecast forecast = new Forecast(BRIDGETOWN, 6F, 23F);
        //when then
        assertThrows(IllegalArgumentException.class, () -> surfingSpotProvider.findBestSpot(date));
    }

}
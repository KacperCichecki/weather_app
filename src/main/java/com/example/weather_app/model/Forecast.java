package com.example.weather_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Forecast {

    private Location location;
    private float windSpeed;
    private float temperature;


    /*
    * Add required filed form this json
    *
    * {
             "data":[
                {
                  "valid_date":"2017-04-01",
                   "ts":1503954000,
                   "datetime":"2017-04-01",
                   "wind_gust_spd":16.7,
                   "wind_spd":6.4,
                   "wind_dir":45,
                   "wind_cdir":"NE",
                   "wind_cdir_full":"northeast",
                   "temp":25,
                   "max_temp":30,
                   "min_temp":26,
                   "high_temp":30,
                   "low_temp":24.5,
                   "app_max_temp":30.64,
                   "app_min_temp":23.64,
                   "pop":0,
                   "precip":0,
                   "snow":0,
                   "snow_depth":0,
                   "slp":1017,
                   "pres":1003.5,
                   "dewpt":17.8,
                   "rh":64.3,
                   "weather":{
                      "icon":"c04d",
                      "code":"804",
                      "description":"Overcast clouds"
                   },
                   "clouds_low":25,
                   "clouds_mid":100,
                   "clouds_hi":50,
                   "clouds":100,
                   "vis":10,
                   "max_dhi":178,
                   "uv":2,
                   "moon_phase":0.99,
                   "moon_phase_lunation":0.48,
                   "moonrise_ts":1530341260,
                   "moonset_ts":1530351260,
                   "sunrise_ts":1530321260,
                   "sunset_ts":1530391260
                }, ...
             ],
             "city_name":"Raleigh",
             "lon":"-78.63861",
             "timezone":"America\/New_York",
             "lat":"35.7721",
             "country_code":"US",
             "state_code":"NC"
          }
    *
    *
    * */
}

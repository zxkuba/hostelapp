package com.zxkuba.reservationapp.controller;

import com.zxkuba.reservationapp.client.OpenWeatherClient;

import com.zxkuba.reservationapp.domain.weather.OpenWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping("v1/weather")
public class OpenWeatherController {

    @Autowired
    private OpenWeatherClient openWeatherClient;

    @GetMapping(value = "getWeather", produces = APPLICATION_JSON_VALUE)
    public OpenWeatherDto getWeather(){
        return openWeatherClient.getWeather();
    }
}

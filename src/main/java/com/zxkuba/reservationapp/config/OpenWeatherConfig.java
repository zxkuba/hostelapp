package com.zxkuba.reservationapp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OpenWeatherConfig {

    @Value("${openweather.api.endpoint.prod}")
    private String openWeatherEndpoint;

    @Value("${openweather.app.key}")
    private String openWeatherAppKey;

    @Value("${openweather.app.cityID}")
    private String cityId;

    @Value("${openweather.app.units}")
    private String units;

}

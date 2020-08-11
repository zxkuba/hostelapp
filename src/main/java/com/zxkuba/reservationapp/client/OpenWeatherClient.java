package com.zxkuba.reservationapp.client;

import com.zxkuba.reservationapp.config.OpenWeatherConfig;
import com.zxkuba.reservationapp.domain.weather.OpenWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class OpenWeatherClient {

    @Autowired
    private OpenWeatherConfig openWeatherConfig;

    @Autowired
    private RestTemplate restTemplate;

    private URI url() {
        return UriComponentsBuilder.fromHttpUrl(openWeatherConfig.getOpenWeatherEndpoint()+ "/weather")
                .queryParam("id", openWeatherConfig.getCityId())
                .queryParam("units", openWeatherConfig.getUnits())
                .queryParam("appid", openWeatherConfig.getOpenWeatherAppKey()).build().encode().toUri();
    }

    public OpenWeatherDto getWeather(){
        return restTemplate.getForObject(url(), OpenWeatherDto.class);
    }

}

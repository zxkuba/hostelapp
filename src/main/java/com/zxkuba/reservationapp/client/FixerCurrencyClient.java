package com.zxkuba.reservationapp.client;


import com.zxkuba.reservationapp.config.FixerCurrencyConfig;
import com.zxkuba.reservationapp.domain.currency.FixerCurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class FixerCurrencyClient {

    @Autowired
    private FixerCurrencyConfig fixerCurrencyConfig;

    @Autowired
    private RestTemplate restTemplate;

    private URI url(){
        return UriComponentsBuilder.fromHttpUrl(fixerCurrencyConfig.getFixerApiEndpoint()+ "/latest")
                .queryParam("access_key" , fixerCurrencyConfig.getFixerAppKey()).build().encode().toUri();
    }

    public FixerCurrencyDto getCurrentPlnCurrency(){
        return restTemplate.getForObject(url(), FixerCurrencyDto.class);
    }
}

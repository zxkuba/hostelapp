package com.zxkuba.reservationapp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FixerCurrencyConfig {

    @Value("${fixer.api.endpoint.prod}")
    private String fixerApiEndpoint;

    @Value("${fixer.app.key}")
    private String fixerAppKey;
}

package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.currency.FixerCurrencyRateDto;
import com.zxkuba.reservationapp.entity.currency.FixerCurrencyRate;
import org.springframework.stereotype.Component;

@Component
public class FixerCurrencyRateMapper {

    public FixerCurrencyRate mapToFixerCurrencyRate(FixerCurrencyRateDto fixerCurrencyRateDto){
        return new FixerCurrencyRate(
                fixerCurrencyRateDto.getId(),
                fixerCurrencyRateDto.getTodayPlnCurrency());
    }

    public FixerCurrencyRateDto mapToFixerCurrencyRateDto(FixerCurrencyRate fixerCurrencyRate){
        return new FixerCurrencyRateDto(
                fixerCurrencyRate.getId(),
                fixerCurrencyRate.getTodayPlnCurrency());
    }
}

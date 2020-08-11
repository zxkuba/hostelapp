package com.zxkuba.reservationapp.mapper;


import com.zxkuba.reservationapp.domain.currency.FixerCurrencyDto;
import com.zxkuba.reservationapp.domain.currency.FixerCurrencyRateDto;
import com.zxkuba.reservationapp.entity.currency.FixerCurrency;
import com.zxkuba.reservationapp.entity.currency.FixerCurrencyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FixerCurrencyMapper {

    private final FixerCurrencyRateMapper fixerCurrencyRateMapper;

    @Autowired
    public FixerCurrencyMapper(FixerCurrencyRateMapper fixerCurrencyRateMapper) {
        this.fixerCurrencyRateMapper = fixerCurrencyRateMapper;
    }

    public FixerCurrency mapToFixerCurrency(final FixerCurrencyDto fixerCurrencyDto){
        FixerCurrencyRate fixerCurrencyRateDto = fixerCurrencyRateMapper.mapToFixerCurrencyRate(fixerCurrencyDto.getFixerCurrencyRateDto());
       return new FixerCurrency(
               fixerCurrencyDto.getId(),
               fixerCurrencyDto.getDate(),
               fixerCurrencyRateDto);
    }

    public FixerCurrencyDto mapToFixerCurrencyDto(final FixerCurrency fixerCurrency){
        FixerCurrencyRateDto fixerCurrencyRate = fixerCurrencyRateMapper.mapToFixerCurrencyRateDto(fixerCurrency.getFixerCurrencyRate());
        return new FixerCurrencyDto(
                fixerCurrency.getId(),
                fixerCurrency.getDate(),
                fixerCurrencyRate);
    }


}

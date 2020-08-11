package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.client.FixerCurrencyClient;
import com.zxkuba.reservationapp.domain.currency.FixerCurrencyDto;
import com.zxkuba.reservationapp.entity.currency.FixerCurrency;
import com.zxkuba.reservationapp.mapper.FixerCurrencyMapper;
import com.zxkuba.reservationapp.repository.PlnCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlnCurrencyService {

    private final PlnCurrencyRepository plnCurrencyRepository;
    private final FixerCurrencyMapper fixerCurrencyMapper;
    private final FixerCurrencyClient fixerCurrencyClient;

    @Autowired
    public PlnCurrencyService(PlnCurrencyRepository plnCurrencyRepository, FixerCurrencyMapper fixerCurrencyMapper,
                              FixerCurrencyClient fixerCurrencyClient) {
        this.plnCurrencyRepository = plnCurrencyRepository;
        this.fixerCurrencyMapper = fixerCurrencyMapper;
        this.fixerCurrencyClient = fixerCurrencyClient;
    }

    public FixerCurrencyDto getCurrency(){
        return fixerCurrencyClient.getCurrentPlnCurrency();
    }

    public FixerCurrencyDto save(FixerCurrencyDto fixerCurrencyDto){
        FixerCurrency fixerCurrency = fixerCurrencyMapper.mapToFixerCurrency(fixerCurrencyDto);
        return fixerCurrencyMapper.mapToFixerCurrencyDto(plnCurrencyRepository.save(fixerCurrency));
    }
}

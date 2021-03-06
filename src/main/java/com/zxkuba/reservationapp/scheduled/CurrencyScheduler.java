package com.zxkuba.reservationapp.scheduled;

import com.zxkuba.reservationapp.client.FixerCurrencyClient;
import com.zxkuba.reservationapp.domain.currency.FixerCurrencyDto;
import com.zxkuba.reservationapp.service.PlnCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencyScheduler {

    private final PlnCurrencyService plnCurrencyService;
    private final FixerCurrencyClient fixerCurrencyClient;

    @Autowired
    public CurrencyScheduler(PlnCurrencyService plnCurrencyService, FixerCurrencyClient fixerCurrencyClient) {
        this.plnCurrencyService = plnCurrencyService;
        this.fixerCurrencyClient = fixerCurrencyClient;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void fetchCurrencyToDb(){
        plnCurrencyService.deleteAll();
      FixerCurrencyDto fixerCurrencyDto = fixerCurrencyClient.getCurrentPlnCurrency();
       plnCurrencyService.save(fixerCurrencyDto);
    }
    
}

package com.zxkuba.reservationapp.controller;

import com.zxkuba.reservationapp.client.FixerCurrencyClient;
import com.zxkuba.reservationapp.domain.CheckOutDto;
import com.zxkuba.reservationapp.domain.currency.FixerCurrencyDto;
import com.zxkuba.reservationapp.exception.CheckOutNotFoundException;
import com.zxkuba.reservationapp.exception.ReservationNotFoundException;
import com.zxkuba.reservationapp.service.CheckOutDbService;
import com.zxkuba.reservationapp.service.PlnCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping ("v1/checkout")
public class CheckOutController {

    private final CheckOutDbService checkOutDbService;
    private final FixerCurrencyClient fixerCurrencyClient;
    private final PlnCurrencyService plnCurrencyService;

    @Autowired
    public CheckOutController(CheckOutDbService checkOutDbService, FixerCurrencyClient fixerCurrencyClient,
                              PlnCurrencyService plnCurrencyService) {
        this.checkOutDbService = checkOutDbService;
        this.fixerCurrencyClient = fixerCurrencyClient;
        this.plnCurrencyService = plnCurrencyService;
    }

    @GetMapping
    public List<CheckOutDto> getAllCheckOuts(){
        return checkOutDbService.getAllCheckOuts();
    }

    @GetMapping(value = "/{checkOutId}", produces = APPLICATION_JSON_VALUE)
    public CheckOutDto getCheckOut(@PathVariable Long checkOutId) throws CheckOutNotFoundException {
        return checkOutDbService.getCheckOutById(checkOutId);
    }

    @DeleteMapping("/{checkOutId}")
    public void deleteCheckOut(@PathVariable Long checkOutId) throws CheckOutNotFoundException{
        CheckOutDto checkOutDto = checkOutDbService.getCheckOutById(checkOutId);

        if(checkOutDto != null){
            checkOutDbService.deleteCheckOut(checkOutId);
        }else {
            throw new CheckOutNotFoundException();
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CheckOutDto createCheckOut(@RequestBody CheckOutDto checkOutDto){
        return checkOutDbService.saveCheckOut(checkOutDto);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public CheckOutDto updateCheckOut(@RequestBody CheckOutDto checkOutDto){
        return checkOutDbService.saveCheckOut(checkOutDto);
    }

    @PutMapping(value = "/{checkOutId}", produces = APPLICATION_JSON_VALUE)
    public void setReservationToCheckOut(@PathVariable Long checkOutId, @RequestBody Long reservationId)
        throws CheckOutNotFoundException, ReservationNotFoundException {
         checkOutDbService.setReservationToCheckOut(checkOutId, reservationId);
    }


    @GetMapping(value = "getEuroPrice", produces = APPLICATION_JSON_VALUE)
    public FixerCurrencyDto getTestCurrency(){
        return plnCurrencyService.getCurrency();
    }


    @GetMapping(value = "createCurrency",produces = APPLICATION_JSON_VALUE)
    public FixerCurrencyDto createCurrentCurrency(){
        FixerCurrencyDto fixerCurrencyDto = fixerCurrencyClient.getCurrentPlnCurrency();
        return plnCurrencyService.save(fixerCurrencyDto);
    }


}

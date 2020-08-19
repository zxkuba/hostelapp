package com.zxkuba.reservationapp.controller;

import com.zxkuba.reservationapp.domain.ReservationDto;
import com.zxkuba.reservationapp.exception.ReservationNotFoundException;
import com.zxkuba.reservationapp.service.ReservationDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping ("/v1/reservation")
public class ReservationController {

    private final ReservationDbService reservationDbService;

    @Autowired
    public ReservationController(ReservationDbService reservationDbService) {
        this.reservationDbService = reservationDbService;
    }

    @GetMapping
    public List<ReservationDto> getAllReservations(){
        return reservationDbService.getAllReservations();
    }

    @GetMapping(value = "/{reservationId}", produces = APPLICATION_JSON_VALUE)
    public ReservationDto getReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        return reservationDbService.getReservationById(reservationId);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) throws ReservationNotFoundException{
        ReservationDto reservationDto = reservationDbService.getReservationById(reservationId);

        if(reservationDto != null){
            reservationDbService.deleteReservation(reservationId);
        }else {
            throw new ReservationNotFoundException();
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ReservationDto createReservation(@RequestBody ReservationDto reservationDto){
        return reservationDbService.saveReservation(reservationDto);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ReservationDto updateReservation(@RequestBody ReservationDto reservationDto){
        return reservationDbService.saveReservation(reservationDto);
    }

}

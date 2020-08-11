package com.zxkuba.reservationapp.controller;

import com.zxkuba.reservationapp.domain.ResidentDto;
import com.zxkuba.reservationapp.exception.ReservationNotFoundException;
import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.service.ResidentDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/resident")
public class ResidentController {

    private final ResidentDbService residentDbService;

    @Autowired
    public ResidentController(ResidentDbService residentDbService) {
        this.residentDbService = residentDbService;
    }

    @GetMapping
    public List<ResidentDto> getAllResidents(){
        return residentDbService.getAllResidents();
    }

    @GetMapping(value = "/{residentId}", produces = APPLICATION_JSON_VALUE)
    public ResidentDto getResident(@PathVariable Long residentId) throws ResidentNotFoundException {
        return residentDbService.getResidentById(residentId);
    }

    @DeleteMapping("/{residentId}")
    public void deleteResident(@PathVariable Long residentId) throws ResidentNotFoundException {
        ResidentDto residentDto = residentDbService.getResidentById(residentId);
        if(residentDto != null){
            residentDbService.deleteResident(residentId);
        }else {
            throw new ResidentNotFoundException();
        }
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResidentDto updateResident(@RequestBody ResidentDto residentDto){
        return residentDbService.saveResident(residentDto);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResidentDto createResident(@RequestBody ResidentDto residentDto){
        return residentDbService.saveResident(residentDto);
    }

    @PutMapping (value = "/{residentId}", produces = APPLICATION_JSON_VALUE)
    public void setReservationToResident(@PathVariable Long residentId, @RequestBody Long reservationId)
    throws ResidentNotFoundException, ReservationNotFoundException {
        residentDbService.setReservationToResident(residentId, reservationId);
    }
}

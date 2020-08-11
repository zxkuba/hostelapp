package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.entity.Reservation;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.domain.ResidentDto;
import com.zxkuba.reservationapp.exception.ReservationNotFoundException;
import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.mapper.ResidentMapper;
import com.zxkuba.reservationapp.repository.ReservationRepository;
import com.zxkuba.reservationapp.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentDbService {

    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;
    private final ReservationRepository reservationRepository;


    @Autowired
    public ResidentDbService(ResidentRepository residentRepository, ResidentMapper residentMapper, ReservationRepository reservationRepository) {
        this.residentRepository = residentRepository;
        this.residentMapper = residentMapper;
        this.reservationRepository = reservationRepository;

    }

    public List<ResidentDto> getAllResidents(){
        List<Resident> residents = residentRepository.findAll();
        return residentMapper.mapToResidentDtoList(residents);
    }

    public ResidentDto saveResident(ResidentDto residentDto){
        Resident resident = residentMapper.mapToResident(residentDto);
        return residentMapper.mapToResidentDto(residentRepository.save(resident));
    }

    public ResidentDto getResidentById(Long residentId) throws ResidentNotFoundException {
        Resident resident = residentRepository.findById(residentId).orElseThrow(ResidentNotFoundException::new);
        return residentMapper.mapToResidentDto(resident);
    }

    public void deleteResident(final Long residentId) {
         residentRepository.deleteById(residentId);
    }

    public void setReservationToResident(Long residentId, Long reservationId) throws ReservationNotFoundException, ResidentNotFoundException{
        Resident residentReservation = residentRepository.findById(residentId).orElseThrow(ResidentNotFoundException::new);
        Reservation reservationResident = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);

        residentReservation.getReservations().add(reservationResident);
        reservationResident.setResident(residentReservation);

        residentRepository.save(residentReservation);
        reservationRepository.save(reservationResident);
    }

}

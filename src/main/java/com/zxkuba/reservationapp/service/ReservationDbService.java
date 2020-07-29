package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.domain.ReservationDto;
import com.zxkuba.reservationapp.entity.Reservation;
import com.zxkuba.reservationapp.exception.ReservationNotFoundException;
import com.zxkuba.reservationapp.mapper.ReservationMapper;
import com.zxkuba.reservationapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationDbService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationDbService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationDto> getAllReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.mapToReservationDtoList(reservations);
    }


    public ReservationDto saveReservation(ReservationDto reservationDto){
        Reservation reservation = reservationMapper.mapToReservation(reservationDto);
        return reservationMapper.mapToReservationDto(reservationRepository.save(reservation));
    }

    public ReservationDto getReservationById(Long reservationId) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        return reservationMapper.mapToReservationDto(reservation);
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}

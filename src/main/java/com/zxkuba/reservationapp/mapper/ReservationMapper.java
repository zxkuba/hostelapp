package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.ReservationDto;
import com.zxkuba.reservationapp.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public Reservation mapToReservation(final ReservationDto reservationDto){
        return new Reservation(
                reservationDto.getId(),
                reservationDto.getStayFrom(),
                reservationDto.getStayTo(),
                reservationDto.getPricePerNight(),
                reservationDto.getResident());
    }

    public ReservationDto mapToReservationDto(final Reservation reservation){
        return new ReservationDto(
                reservation.getId(),
                reservation.getStayFrom(),
                reservation.getStayTo(),
                reservation.getPricePerNight(),
                reservation.getResident());
    }

    public List<ReservationDto> mapToReservationDtoList(final List<Reservation> reservations){
        return reservations.stream()
                .map(this::mapToReservationDto)
                .collect(Collectors.toList());
    }
}

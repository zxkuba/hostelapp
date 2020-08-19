package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.ReservationDto;

import com.zxkuba.reservationapp.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationMapperTest {

    @Autowired
    ReservationMapper reservationMapper;

    private Reservation createReservation(){
        return Reservation.builder()
                .stayFrom(LocalDate.of(2020, 8, 31))
                .stayTo(LocalDate.of(2020,9,15))
                .pricePerNight(new BigDecimal(150))
                .build();
    }

    private ReservationDto createReservationDto(){
        return ReservationDto.builder()
                .stayFrom(LocalDate.of(2020, 8, 31))
                .stayTo(LocalDate.of(2020,9,15))
                .pricePerNight(new BigDecimal(150))
                .build();
    }

    @Test
    public void shouldMapToReservation(){
        //Given
        ReservationDto reservationDto = createReservationDto();
        //When
        Reservation mapToReservation = reservationMapper.mapToReservation(reservationDto);
        //Then
        assertEquals(reservationDto.getId(), mapToReservation.getId());
        assertEquals(reservationDto.getStayFrom(), mapToReservation.getStayFrom());
        assertEquals(reservationDto.getStayTo(), mapToReservation.getStayTo());
        assertEquals(reservationDto.getPricePerNight(), mapToReservation.getPricePerNight());
    }

    @Test
    public void shouldMapToReservationDto(){
        //Given
        Reservation reservation = createReservation();
        //When
        ReservationDto mapToReservationDto = reservationMapper.mapToReservationDto(reservation);
        //Then
        assertEquals(reservation.getId(), mapToReservationDto.getId());
        assertEquals(reservation.getStayFrom(), mapToReservationDto.getStayFrom());
        assertEquals(reservation.getStayTo(), mapToReservationDto.getStayTo());
        assertEquals(reservation.getPricePerNight(), mapToReservationDto.getPricePerNight());
    }

    @Test
    public void shouldMapToReservationListDto(){
        //Given
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(createReservation());
        //When
        List<ReservationDto> mapToReservationDtoList = reservationMapper.mapToReservationDtoList(reservationList);
        //Then
        assertEquals(1, mapToReservationDtoList.size());
    }
}
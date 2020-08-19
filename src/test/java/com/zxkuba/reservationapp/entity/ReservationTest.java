package com.zxkuba.reservationapp.entity;

import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ReservationTest {

    @Autowired
    ReservationRepository reservationRepository;

    private Reservation createReservation(){
        return Reservation.builder()
                .stayFrom(LocalDate.of(2020, 8, 31))
                .stayTo(LocalDate.of(2020,9,15))
                .pricePerNight(new BigDecimal(150))
                .build();
    }

    @Test
    public void shouldFetchAllReservations(){
        //Given
        Reservation reservation = createReservation();
        Reservation reservation1 = createReservation();
        Reservation saveReservation = reservationRepository.save(reservation);
        Reservation saveReservation1 = reservationRepository.save(reservation1);
        //When
        List<Reservation> reservationList = reservationRepository.findAll();
        //Then
        assertEquals(2, reservationList.size());
        //CleanUp
        reservationRepository.deleteById(saveReservation.getId());
        reservationRepository.deleteById(saveReservation1.getId());
    }

    @Test
    public void shouldFetchReservation() throws ResidentNotFoundException {
        //Given
        Reservation reservation = createReservation();
        Reservation saveReservation = reservationRepository.save(reservation);
        //When
        Reservation findReservation = reservationRepository.findById(reservation.getId()).orElseThrow(ResidentNotFoundException::new);
        //Then
        assertNotNull(findReservation.getId());
        //CleanUp
        reservationRepository.deleteById(saveReservation.getId());
    }

    @Test
    public void shouldSaveReservation(){
        //Given
        Reservation reservation = createReservation();
        //When
        Reservation saveReservation = reservationRepository.save(reservation);
        //Then
        assertNotNull(saveReservation.getId());
        //CleanUp
        reservationRepository.deleteById(saveReservation.getId());
    }

    @Test
    public void shouldUpdateReservation(){
        //Given
        Reservation reservation = createReservation();
        Reservation saveReservation = reservationRepository.save(reservation);
        //When
        Long id = saveReservation.getId();
        Reservation updateReservation = Reservation.builder()
                .id(id)
                .stayFrom(LocalDate.of(2020,9,2))
                .stayTo(LocalDate.of(2020,9,18))
                .pricePerNight(new BigDecimal(100))
                .build();
        reservationRepository.save(updateReservation);
        //Then
        assertEquals(reservation.getId(), updateReservation.getId());
        assertEquals(LocalDate.of(2020,9,2), updateReservation.getStayFrom());
        assertEquals(LocalDate.of(2020,9,18), updateReservation.getStayTo());
        assertEquals(new BigDecimal(100), updateReservation.getPricePerNight());
        //CleanUp
        reservationRepository.deleteById(saveReservation.getId());
    }

    @Test
    public void shouldDeleteReservation(){
        //Given
        Reservation reservation = createReservation();
        Reservation saveReservation = reservationRepository.save(reservation);
        //When
        reservationRepository.deleteById(saveReservation.getId());
        List<Reservation> reservationList = reservationRepository.findAll();
        //Then
        assertEquals(0, reservationList.size());
    }

}
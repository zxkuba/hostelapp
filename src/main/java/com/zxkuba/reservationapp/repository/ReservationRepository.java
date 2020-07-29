package com.zxkuba.reservationapp.repository;

import com.zxkuba.reservationapp.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Override
    List<Reservation> findAll();

    @Override
    Optional<Reservation> findById(Long reservationId);

    @Override
    Reservation save(Reservation reservation);

    @Override
    void deleteById(Long reservationId);

}

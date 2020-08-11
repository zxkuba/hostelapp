package com.zxkuba.reservationapp.repository;

import com.zxkuba.reservationapp.entity.CheckOut;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CheckOutRepository extends CrudRepository<CheckOut, Long> {

    @Override
    List<CheckOut> findAll();

    @Override
    Optional<CheckOut> findById(Long checkOutId);

    @Override
    CheckOut save(CheckOut checkOut);

    @Override
    void deleteById(Long checkOutId);
}

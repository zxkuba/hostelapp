package com.zxkuba.reservationapp.repository;

import com.zxkuba.reservationapp.entity.Resident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ResidentRepository extends CrudRepository<Resident, Long> {

    @Override
    List<Resident> findAll();

    @Override
    Optional<Resident> findById(Long residentId);

    @Override
    Resident save(Resident resident);

    @Override
    void deleteById(Long residentId);

}

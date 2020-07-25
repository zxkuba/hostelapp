package com.zxkuba.reservationapp.repository;

import com.zxkuba.reservationapp.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Override
    List<Room> findAll();

    @Override
    Optional<Room> findById(Long roomId);

    @Override
    Room save(Room room);

    @Override
    void deleteById(Long roomId);
}

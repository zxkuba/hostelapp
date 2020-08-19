package com.zxkuba.reservationapp.entity;

import com.zxkuba.reservationapp.exception.RoomNotFoundException;
import com.zxkuba.reservationapp.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RoomTest {

    @Autowired
    RoomRepository roomRepository;

    private Room createRoom(){
        return Room.builder()
                .flatNumber(104)
                .totalQuantityOfBeds(8)
                .currentBedsQuantity(6)
                .build();
    }

    @Test
    public void shouldFetchAllRooms(){
        //Given
        Room room = createRoom();
        Room room1 = createRoom();
        Room room2 = createRoom();
        Room saveRoom = roomRepository.save(room);
        Room saveRoom1 = roomRepository.save(room1);
        Room saveRoom2 = roomRepository.save(room2);
        //When
        List<Room> rooms = roomRepository.findAll();
        //Then
        assertEquals(3, rooms.size());
        //CleanUp
        roomRepository.deleteById(saveRoom.getId());
        roomRepository.deleteById(saveRoom1.getId());
        roomRepository.deleteById(saveRoom2.getId());
    }

    @Test
    public void shouldFetchRoom() throws RoomNotFoundException {
        //Given
        Room room = createRoom();
        Room saveRoom = roomRepository.save(room);
        //When
        Room findRoom = roomRepository.findById(room.getId()).orElseThrow(RoomNotFoundException::new);
        //Then
        assertNotNull(findRoom.getId());
        //CleanUp
        roomRepository.deleteById(saveRoom.getId());
    }

    @Test
    public void shouldSaveRoom(){
        //Given
        Room room = createRoom();
        //When
        Room saveRoom = roomRepository.save(room);
        //Then
        assertNotNull(saveRoom.getId());
        //CleanUp
        roomRepository.deleteById(saveRoom.getId());
    }

    @Transactional
    @Test
    public void shouldUpdateRoom(){
        //Given
        Room room = createRoom();
        Room saveRoom = roomRepository.save(room);
        Long id = saveRoom.getId();
        //When
        Room updateRoom = Room.builder()
                .id(id)
                .totalQuantityOfBeds(4)
                .currentBedsQuantity(2)
                .build();
        roomRepository.save(updateRoom);
        //Then
        assertEquals(saveRoom.getId(), updateRoom.getId());
        assertEquals(4, updateRoom.getTotalQuantityOfBeds());
        assertEquals(2, updateRoom.getCurrentBedsQuantity());
        //CleanUp
        roomRepository.deleteById(saveRoom.getId());
    }

    @Test
    public void shouldDeletedRoom(){
        //Given
        Room room = createRoom();
        Room saveRoom = roomRepository.save(room);
        //When
        roomRepository.deleteById(saveRoom.getId());
        List<Room> rooms = roomRepository.findAll();
        //Then
        assertEquals(0, rooms.size());

    }

}
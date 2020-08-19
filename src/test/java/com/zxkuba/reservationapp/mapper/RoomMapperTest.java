package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.RoomDto;
import com.zxkuba.reservationapp.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomMapperTest {

    @Autowired
    RoomMapper roomMapper;

    private Room createRoom(){
        return Room.builder()
                .flatNumber(104)
                .totalQuantityOfBeds(8)
                .currentBedsQuantity(6)
                .build();
    }

    private RoomDto createRoomDto(){
        return RoomDto.builder()
                .flatNumber(104)
                .totalQuantityOfBeds(8)
                .currentBedsQuantity(6)
                .build();
    }
    @Test
    public void shouldMapToRoom(){
        //Given
        RoomDto roomDto = createRoomDto();
        //When
        Room mapToRoom = roomMapper.mapToRoom(roomDto);
        //Then
        assertEquals(roomDto.getId(), mapToRoom.getId());
        assertEquals(roomDto.getCurrentBedsQuantity(), mapToRoom.getCurrentBedsQuantity());
        assertEquals(roomDto.getFlatNumber(), mapToRoom.getFlatNumber());
        assertEquals(roomDto.getTotalQuantityOfBeds(), mapToRoom.getTotalQuantityOfBeds());
    }
    @Test
    public void shouldMapToRoomDto(){
        //Given
        Room room = createRoom();
        //When
        RoomDto mapToRoomDto = roomMapper.mapToRoomDto(room);
        //Then
        assertEquals(room.getId(), mapToRoomDto.getId());
        assertEquals(room.getCurrentBedsQuantity(), mapToRoomDto.getCurrentBedsQuantity());
        assertEquals(room.getFlatNumber(), mapToRoomDto.getFlatNumber());
        assertEquals(room.getTotalQuantityOfBeds(), mapToRoomDto.getTotalQuantityOfBeds());
    }

    @Test
    public void shouldMapToRoomDtoList(){
        //Given
        List<Room> roomList = new ArrayList<>();
        roomList.add(createRoom());
        //When
        List<RoomDto> mapToRoomDtoList = roomMapper.mapToRoomDtoList(roomList);
        //Then
        assertEquals(1, mapToRoomDtoList.size());
    }

}
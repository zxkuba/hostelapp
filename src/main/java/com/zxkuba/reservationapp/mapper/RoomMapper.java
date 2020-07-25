package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.Room;
import com.zxkuba.reservationapp.dto.RoomDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    public Room mapToRoom(final RoomDto roomDto){
        return new Room(
                roomDto.getId(),
                roomDto.getFlatNumber(),
                roomDto.getTotalQuantityOfBeds(),
                roomDto.getCurrentBedsQuantity());
    }

    public RoomDto mapToRoomDto(final Room room){
        return new RoomDto(
                room.getId(),
                room.getFlatNumber(),
                room.getTotalQuantityOfBeds(),
                room.getCurrentBedsQuantity());
    }

    public List<RoomDto> mapToRoomDtoList(final List<Room> roomList){
        return roomList.stream()
                .map(this::mapToRoomDto)
                .collect(Collectors.toList());
    }
}

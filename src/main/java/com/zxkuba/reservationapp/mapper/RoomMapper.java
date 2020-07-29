package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.entity.Room;
import com.zxkuba.reservationapp.domain.RoomDto;
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
                roomDto.getResidents(),
                roomDto.getCurrentBedsQuantity());
    }

    public RoomDto mapToRoomDto(final Room room){
        return new RoomDto(
                room.getId(),
                room.getFlatNumber(),
                room.getTotalQuantityOfBeds(),
                room.getResidents(),
                room.getCurrentBedsQuantity());
    }

    public List<RoomDto> mapToRoomDtoList(final List<Room> roomList){
        return roomList.stream()
                .map(this::mapToRoomDto)
                .collect(Collectors.toList());
    }
}

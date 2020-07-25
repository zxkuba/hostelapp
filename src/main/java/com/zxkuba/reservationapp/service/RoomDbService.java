package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.domain.Room;
import com.zxkuba.reservationapp.dto.RoomDto;
import com.zxkuba.reservationapp.exception.NoElementFoundException;
import com.zxkuba.reservationapp.mapper.RoomMapper;
import com.zxkuba.reservationapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomDbService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Autowired
    public RoomDbService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public List<RoomDto> getAllRooms(){
        List<Room> roomList = roomRepository.findAll();
        return roomMapper.mapToRoomDtoList(roomList);
    }

    public RoomDto saveRoom(RoomDto roomDto){
        Room room = roomMapper.mapToRoom(roomDto);
        return roomMapper.mapToRoomDto(roomRepository.save(room));
    }

    public RoomDto getRoomById(Long roomId) throws NoElementFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(NoElementFoundException::new);
        return roomMapper.mapToRoomDto(room);
    }

    public void delete(final Long roomId){
        roomRepository.deleteById(roomId);
    }


}

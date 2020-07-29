package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.entity.Room;
import com.zxkuba.reservationapp.domain.RoomDto;
import com.zxkuba.reservationapp.exception.NoElementFoundException;
import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.exception.RoomNotFoundException;
import com.zxkuba.reservationapp.mapper.RoomMapper;
import com.zxkuba.reservationapp.repository.ResidentRepository;
import com.zxkuba.reservationapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomDbService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final ResidentRepository residentRepository;

    @Autowired
    public RoomDbService(RoomRepository roomRepository, RoomMapper roomMapper, ResidentRepository residentRepository) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
        this.residentRepository = residentRepository;
    }

    public List<RoomDto> getAllRooms(){
        List<Room> roomList = roomRepository.findAll();
        return roomMapper.mapToRoomDtoList(roomList);
    }

    public RoomDto saveRoom(RoomDto roomDto) {

        Room room = roomMapper.mapToRoom(roomDto);
        return roomMapper.mapToRoomDto(roomRepository.save(room));
    }

    public RoomDto getRoomById(Long roomId) throws RoomNotFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
        return roomMapper.mapToRoomDto(room);
    }

    public void delete(final Long roomId){
        roomRepository.deleteById(roomId);
    }

    public void setResidentToRoom(Long roomId, Long residentId) throws RoomNotFoundException, ResidentNotFoundException {
        Room roomResident = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
        Resident residentRoom = residentRepository.findById(residentId).orElseThrow(ResidentNotFoundException::new);

        roomResident.getResidents().add(residentRoom);
        residentRoom.setRoom(roomResident);

        roomRepository.save(roomResident);
        residentRepository.save(residentRoom);
    }
}

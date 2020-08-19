package com.zxkuba.reservationapp.controller;

import com.zxkuba.reservationapp.domain.RoomDto;
import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.exception.RoomNotFoundException;
import com.zxkuba.reservationapp.service.RoomDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/room")
public class RoomController {

    private final RoomDbService roomDbService;

    @Autowired
    public RoomController(RoomDbService roomDbService) {
        this.roomDbService = roomDbService;
    }

    @GetMapping
    public List<RoomDto> getAllRooms(){
        return roomDbService.getAllRooms();
    }

    @GetMapping(value = "/{roomId}", produces = APPLICATION_JSON_VALUE)
    public RoomDto getRoom(@PathVariable Long roomId) throws RoomNotFoundException {
        return roomDbService.getRoomById(roomId);
    }

    @DeleteMapping(value = "/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) throws RoomNotFoundException {
        RoomDto roomDto = roomDbService.getRoomById(roomId);
        if(roomDto != null){
            roomDbService.delete(roomId);
        }else {
            throw new RoomNotFoundException();
        }
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public RoomDto updateRoom(@RequestBody RoomDto roomDto){
        return roomDbService.saveRoom(roomDto);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public RoomDto createRoom(@RequestBody RoomDto roomDto){
        return roomDbService.saveRoom(roomDto);
    }

    @PutMapping(value = "/{roomId}" , produces = APPLICATION_JSON_VALUE)
    public void setResidentToRoom(@PathVariable Long roomId, @RequestBody Long residentId)
            throws RoomNotFoundException, ResidentNotFoundException {
        roomDbService.setResidentToRoom(roomId, residentId);

    }
}

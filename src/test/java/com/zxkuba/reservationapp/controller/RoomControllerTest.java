package com.zxkuba.reservationapp.controller;

import com.google.gson.Gson;
import com.zxkuba.reservationapp.domain.RoomDto;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.entity.Room;

import com.zxkuba.reservationapp.service.RoomDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RoomDbService roomDbService;

    @Test
    public void shouldFetchEmptyRoomList() throws Exception{
        //Given
        List<RoomDto> roomDtoList = new ArrayList<>();
        when(roomDbService.getAllRooms()).thenReturn(roomDtoList);
        //When & Then
        mockMvc.perform(get("/v1/room").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchRoomList() throws Exception{
        //Given
        Resident residentDto = Resident.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        Set<Resident> residentSet = new HashSet<>();
        residentSet.add(residentDto);
        RoomDto roomDto = RoomDto.builder()
                .id(1L)
                .flatNumber(4)
                .totalQuantityOfBeds(8)
                .residents(residentSet)
                .build();
        List<RoomDto> roomDtoList = new ArrayList<>();
        roomDtoList.add(roomDto);
        when(roomDbService.getAllRooms()).thenReturn(roomDtoList);
        //When & Then
        mockMvc.perform(get("/v1/room").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldFetchRoom() throws Exception{
        //Given
        Resident residentDto = Resident.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        Set<Resident> residentSet = new HashSet<>();
        residentSet.add(residentDto);
        RoomDto roomDto = RoomDto.builder()
                .id(1L)
                .flatNumber(4)
                .totalQuantityOfBeds(8)
                .residents(residentSet)
                .build();
        when(roomDbService.getRoomById(roomDto.getId())).thenReturn(roomDto);
        //When & Then
        mockMvc.perform(get("/v1/room").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void shouldCreateRoom() throws Exception{
        //Given
        Resident residentDto = Resident.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        Set<Resident> residentSet = new HashSet<>();
        residentSet.add(residentDto);
        RoomDto roomDto = RoomDto.builder()
                .id(1L)
                .flatNumber(4)
                .totalQuantityOfBeds(8)
                .residents(residentSet)
                .build();
        when(roomDbService.saveRoom(ArgumentMatchers.any(RoomDto.class))).thenReturn(roomDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(residentDto);
        //When @ Then
        mockMvc.perform(post("/v1/room").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldUpdateRoom() throws Exception{
        //Given
        Resident residentDto = Resident.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        Set<Resident> residentSet = new HashSet<>();
        residentSet.add(residentDto);
        RoomDto roomDto = RoomDto.builder()
                .id(1L)
                .flatNumber(4)
                .totalQuantityOfBeds(8)
                .residents(residentSet)
                .build();
        RoomDto roomDto1 = RoomDto.builder()
                .id(1L)
                .flatNumber(8)
                .totalQuantityOfBeds(16)
                .residents(residentSet)
                .build();
       when(roomDbService.saveRoom(ArgumentMatchers.any(RoomDto.class))).thenReturn(roomDto);
       when(roomDbService.saveRoom(ArgumentMatchers.any(RoomDto.class))).thenReturn(roomDto1);
       Gson gson = new Gson();
       String jsonContent = gson.toJson(roomDto1);
        //When & Then
        mockMvc.perform(put("/v1/room").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldDeleteRoom() throws Exception{
        //Given
        RoomDto roomDto = RoomDto.builder()
                .id(1L)
                .flatNumber(4)
                .totalQuantityOfBeds(8)
                .build();
        when(roomDbService.getRoomById(roomDto.getId())).thenReturn(roomDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/room/{roomId}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
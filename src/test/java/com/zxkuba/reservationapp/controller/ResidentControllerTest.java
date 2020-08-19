package com.zxkuba.reservationapp.controller;

import com.google.gson.Gson;
import com.zxkuba.reservationapp.domain.ResidentDto;
import com.zxkuba.reservationapp.entity.Reservation;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.entity.Room;
import com.zxkuba.reservationapp.service.ResidentDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ResidentController.class)
class ResidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ResidentDbService residentDbService;

    @Test
    public void shouldFetchEmptyResidentList() throws Exception{
        //Given
        List<ResidentDto> residentDtoList = new ArrayList<>();
        when(residentDbService.getAllResidents()).thenReturn(residentDtoList);
        //When & Then
        mockMvc.perform(get("/v1/resident").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldFetchResidentList() throws Exception{
        //Given
        Reservation reservationDto = Reservation.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .pricePerNight(new BigDecimal(100))
                .resident(Resident.builder().build())
                .build();
        Set<Reservation> reservationSet = new HashSet<>();
        reservationSet.add(reservationDto);
        ResidentDto residentDto = ResidentDto.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .reservations(reservationSet)
                .build();
        List<ResidentDto> residentDtoList = new ArrayList<>();
        residentDtoList.add(residentDto);
        when(residentDbService.getAllResidents()).thenReturn(residentDtoList);
        //When & Then
        mockMvc.perform(get("/v1/resident").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldFetchResident() throws Exception{
        //Given
        Reservation reservationDto = Reservation.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .pricePerNight(new BigDecimal(100))
                .resident(Resident.builder().build())
                .build();
        Set<Reservation> reservationSet = new HashSet<>();
        reservationSet.add(reservationDto);
        ResidentDto residentDto = ResidentDto.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .reservations(reservationSet)
                .build();
        when(residentDbService.getResidentById(residentDto.getId())).thenReturn(residentDto);
        //When & Then
        mockMvc.perform(get("/v1/resident").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldCreateResident() throws Exception{
        //Given
        Reservation reservationDto = Reservation.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .pricePerNight(new BigDecimal(100))
                .resident(Resident.builder().build())
                .build();
        Set<Reservation> reservationSet = new HashSet<>();
        reservationSet.add(reservationDto);
        ResidentDto residentDto = ResidentDto.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .reservations(reservationSet)
                .build();
        when(residentDbService.saveResident(ArgumentMatchers.any(ResidentDto.class))).thenReturn(residentDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(reservationDto);
        //When & Then
        mockMvc.perform(post("/v1/resident").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldUpdateResident() throws Exception {
        //Given
        ResidentDto residentDto = ResidentDto.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        ResidentDto residentDto1 = ResidentDto.builder()
                .id(1L)
                .firstName("Piotr")
                .lastName("Nowak")
                .room(Room.builder().build())
                .build();
        when(residentDbService.saveResident(ArgumentMatchers.any(ResidentDto.class))).thenReturn(residentDto);
        when(residentDbService.saveResident(ArgumentMatchers.any(ResidentDto.class))).thenReturn(residentDto1);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(residentDto1);
        //When & Then
        mockMvc.perform(put("/v1/resident").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldDeleteResident() throws Exception{
        //Given
        ResidentDto residentDto = ResidentDto.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        when(residentDbService.getResidentById(residentDto.getId())).thenReturn(residentDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/resident/{residentId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}
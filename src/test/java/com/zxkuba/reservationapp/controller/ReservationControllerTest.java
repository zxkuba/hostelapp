package com.zxkuba.reservationapp.controller;

import com.google.gson.Gson;
import com.zxkuba.reservationapp.domain.ReservationDto;
import com.zxkuba.reservationapp.domain.ResidentDto;
import com.zxkuba.reservationapp.entity.Reservation;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.entity.Room;
import com.zxkuba.reservationapp.service.ReservationDbService;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReservationDbService reservationDbService;

    @Test
    public void shouldFetchEmptyReservationList() throws Exception{
        //Given
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        when(reservationDbService.getAllReservations()).thenReturn(reservationDtoList);
        //When & Then
        mockMvc.perform(get("/v1/reservation").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldFetchReservationList() throws Exception{
        //Given
        ReservationDto reservationDto = ReservationDto.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .pricePerNight(new BigDecimal(100))
                .resident(Resident.builder().build())
                .build();
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        reservationDtoList.add(reservationDto);
        when(reservationDbService.getAllReservations()).thenReturn(reservationDtoList);
        //When & Then
        mockMvc.perform(get("/v1/reservation").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldFetchReservation() throws Exception{
        //Given
        ReservationDto reservationDto = ReservationDto.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .pricePerNight(new BigDecimal(100))
                .resident(Resident.builder().build())
                .build();
        when(reservationDbService.getReservationById(reservationDto.getId())).thenReturn(reservationDto);
        //When & Then
        mockMvc.perform(get("/v1/reservation").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldCreateReservation() throws Exception{
        //Given
        Resident residentDto = Resident.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        ReservationDto reservationDto = ReservationDto.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .resident(residentDto)
                .pricePerNight(new BigDecimal(100))
                .build();
        when(reservationDbService.saveReservation(ArgumentMatchers.any(ReservationDto.class))).thenReturn(reservationDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(residentDto);
        //When & Then
        mockMvc.perform(post("/v1/reservation").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldUpdateReservation() throws Exception{
        //Given
        Resident residentDto = Resident.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .room(Room.builder().build())
                .build();
        ReservationDto reservationDto = ReservationDto.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .resident(residentDto)
                .pricePerNight(new BigDecimal(100))
                .build();
        ReservationDto reservationDto1 = ReservationDto.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 25))
                .stayTo(LocalDate.of(2020, 10, 10))
                .resident(residentDto)
                .pricePerNight(new BigDecimal(150))
                .build();
        when(reservationDbService.saveReservation(ArgumentMatchers.any(ReservationDto.class))).thenReturn(reservationDto);
        when(reservationDbService.saveReservation(ArgumentMatchers.any(ReservationDto.class))).thenReturn(reservationDto1);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(residentDto);
        //When & Then
        mockMvc.perform(put("/v1/reservation").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldDeleteReservation() throws Exception{
        //Given
        ReservationDto reservationDto = ReservationDto.builder()
                .id(1L)
                .stayFrom(LocalDate.of(2020, 9, 15))
                .stayTo(LocalDate.of(2020, 9, 30))
                .pricePerNight(new BigDecimal(100))
                .build();
        when(reservationDbService.getReservationById(reservationDto.getId())).thenReturn(reservationDto);
        //When @ Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/reservation/{reservationId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
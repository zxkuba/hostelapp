package com.zxkuba.reservationapp.controller;

import com.google.gson.Gson;
import com.zxkuba.reservationapp.client.FixerCurrencyClient;
import com.zxkuba.reservationapp.domain.CheckOutDto;
import com.zxkuba.reservationapp.domain.ReservationDto;
import com.zxkuba.reservationapp.domain.RoomDto;
import com.zxkuba.reservationapp.entity.CheckOut;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.entity.Room;
import com.zxkuba.reservationapp.service.CheckOutDbService;
import com.zxkuba.reservationapp.service.PlnCurrencyService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckOutController.class)
class CheckOutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CheckOutDbService checkOutDbService;

    @MockBean
    FixerCurrencyClient fixerCurrencyClient;

    @MockBean
    PlnCurrencyService plnCurrencyService;

    @Test
    public void shouldFetchEmptyCheckOutList() throws Exception{
        //Given
        List<CheckOutDto> checkOutDtoArrayList = new ArrayList<>();
        when(checkOutDbService.getAllCheckOuts()).thenReturn(checkOutDtoArrayList);
        //When & Then
        mockMvc.perform(get("/v1/checkout").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldFetchCheckOutList() throws Exception{
        //Given
        CheckOutDto checkOutDto = CheckOutDto.builder()
                .id(1L)
                .stayLength(20)
                .totalPrice(new BigDecimal(4000))
                .euroTotalPrice(new BigDecimal(1000))
                .build();
        List<CheckOutDto> checkOutDtoList = new ArrayList<>();
        checkOutDtoList.add(checkOutDto);
        when(checkOutDbService.getAllCheckOuts()).thenReturn(checkOutDtoList);
        //When & Then
        mockMvc.perform(get("/v1/checkout").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldFetchCheckOut() throws Exception{
        //Given
        CheckOutDto checkOutDto = CheckOutDto.builder()
                .id(1L)
                .stayLength(20)
                .totalPrice(new BigDecimal(4000))
                .euroTotalPrice(new BigDecimal(1000))
                .build();
        when(checkOutDbService.getCheckOutById(checkOutDto.getId())).thenReturn(checkOutDto);
        //When & Then
        mockMvc.perform(get("/v1/checkout").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldCreateCheckOut() throws Exception{
        //Given
        CheckOutDto checkOutDto = CheckOutDto.builder()
                .id(1L)
                .stayLength(20)
                .totalPrice(new BigDecimal(4000))
                .euroTotalPrice(new BigDecimal(1000))
                .build();
        when(checkOutDbService.saveCheckOut(ArgumentMatchers.any(CheckOutDto.class))).thenReturn(checkOutDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(checkOutDto);
        //When & Then
        mockMvc.perform(post("/v1/checkout").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldUpdateCheckOut() throws Exception{
        //Given
        CheckOutDto checkOutDto = CheckOutDto.builder()
                .id(1L)
                .stayLength(20)
                .totalPrice(new BigDecimal(4000))
                .euroTotalPrice(new BigDecimal(1000))
                .build();
        CheckOutDto checkOutDto1 = CheckOutDto.builder()
                .id(1L)
                .stayLength(10)
                .totalPrice(new BigDecimal(2000))
                .euroTotalPrice(new BigDecimal(500))
                .build();
        when(checkOutDbService.saveCheckOut(ArgumentMatchers.any(CheckOutDto.class))).thenReturn(checkOutDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(checkOutDto1);
        //When & Then
        mockMvc.perform(put("/v1/checkout").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldDeleteCheckOut() throws Exception{
        //Given
        CheckOutDto checkOutDto = CheckOutDto.builder()
                .id(1L)
                .stayLength(20)
                .totalPrice(new BigDecimal(4000))
                .euroTotalPrice(new BigDecimal(1000))
                .build();
        when(checkOutDbService.getCheckOutById(checkOutDto.getId())).thenReturn(checkOutDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/checkout/{CheckOutId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


}
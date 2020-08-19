package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.CheckOutDto;
import com.zxkuba.reservationapp.entity.CheckOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckOutMapperTest {

    @Autowired
    CheckOutMapper checkOutMapper;

    private CheckOut createCheckOut(){
        return CheckOut.builder()
                .stayLength(25)
                .totalPrice(new BigDecimal(8000))
                .euroTotalPrice(new BigDecimal(2000))
                .build();
    }

    private CheckOutDto createCheckOutDto(){
        return CheckOutDto.builder()
                .stayLength(25)
                .totalPrice(new BigDecimal(8000))
                .euroTotalPrice(new BigDecimal(2000))
                .build();
    }

    @Test
    public void shouldMapToCheckOut(){
        //Given
        CheckOutDto checkOutDto = createCheckOutDto();
        //When
        CheckOut mapToCheckOut = checkOutMapper.mapToCheckOut(checkOutDto);
        //Then
        assertEquals(checkOutDto.getId(), mapToCheckOut.getId());
        assertEquals(checkOutDto.getStayLength(), mapToCheckOut.getStayLength());
        assertEquals(checkOutDto.getTotalPrice(), mapToCheckOut.getTotalPrice());
        assertEquals(checkOutDto.getEuroTotalPrice(), mapToCheckOut.getEuroTotalPrice());
    }
    @Test
    public void shouldMapToCheckOutDto(){
        //Given
        CheckOut checkOut = createCheckOut();
        //When
        CheckOutDto mapToCheckOutDto = checkOutMapper.mapToCheckOutDto(checkOut);
        //Then
        assertEquals(checkOut.getId(), mapToCheckOutDto.getId());
        assertEquals(checkOut.getTotalPrice(), mapToCheckOutDto.getTotalPrice());
        assertEquals(checkOut.getStayLength(), mapToCheckOutDto.getStayLength());
        assertEquals(checkOut.getEuroTotalPrice(), mapToCheckOutDto.getEuroTotalPrice());
    }

    @Test
    public void shouldMapToCheckOutDtoList(){
        //Given
        List<CheckOut> checkOutList = new ArrayList<>();
        checkOutList.add(createCheckOut());
        //When
        List<CheckOutDto> mapToCheckOutDtoList = checkOutMapper.mapToCheckOutDtoList(checkOutList);
        //Then
        assertEquals(1, mapToCheckOutDtoList.size());
    }
}
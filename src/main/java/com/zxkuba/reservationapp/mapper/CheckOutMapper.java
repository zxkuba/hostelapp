package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.CheckOutDto;
import com.zxkuba.reservationapp.entity.CheckOut;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckOutMapper {


    public CheckOut mapToCheckOut(final CheckOutDto checkOutDto){

        return  new CheckOut(
                checkOutDto.getId(),
                checkOutDto.getReservation(),
                checkOutDto.getStayLength(),
                checkOutDto.getTotalPrice(),
                checkOutDto.getEuroTotalPrice());
    }

    public CheckOutDto mapToCheckOutDto(final CheckOut checkOut){
        return new CheckOutDto(
                checkOut.getId(),
                checkOut.getReservation(),
                checkOut.getStayLength(),
                checkOut.getTotalPrice(),
                checkOut.getEuroTotalPrice());
    }

    public List<CheckOutDto> mapToCheckOutDtoList (final List<CheckOut> checkOutList){
        return checkOutList.stream()
                .map(this::mapToCheckOutDto)
                .collect(Collectors.toList());
    }
}

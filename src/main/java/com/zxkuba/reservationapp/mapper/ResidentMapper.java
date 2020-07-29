package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.domain.ResidentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResidentMapper {

    public Resident mapToResident(final ResidentDto residentDto){
        return new Resident(
                residentDto.getId(),
                residentDto.getFirstName(),
                residentDto.getLastName(),
                residentDto.getRoom(),
                residentDto.getReservations());
    }

    public ResidentDto mapToResidentDto(final Resident resident){
        return new ResidentDto(
                resident.getId(),
                resident.getFirstName(),
                resident.getLastName(),
                resident.getRoom(),
                resident.getReservations());

    }

    public List<ResidentDto> mapToResidentDtoList(final List<Resident> residentList){
        return residentList.stream()
                .map(this::mapToResidentDto)
                .collect(Collectors.toList());
    }

}

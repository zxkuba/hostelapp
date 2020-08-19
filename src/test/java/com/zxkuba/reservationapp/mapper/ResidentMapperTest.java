package com.zxkuba.reservationapp.mapper;

import com.zxkuba.reservationapp.domain.ResidentDto;
import com.zxkuba.reservationapp.entity.Resident;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResidentMapperTest {

    @Autowired
    ResidentMapper residentMapper;

    private Resident createResident(){
        return Resident.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .build();
    }

    private ResidentDto createResidentDto(){
        return ResidentDto.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .build();
    }

    @Test
    public void shouldMapToResident(){
        //Given
        ResidentDto residentDto = createResidentDto();
        //When
        Resident mapToResident = residentMapper.mapToResident(residentDto);
        //Then
        assertEquals(residentDto.getId(), mapToResident.getId());
        assertEquals(residentDto.getFirstName(), mapToResident.getFirstName());
        assertEquals(residentDto.getLastName(), mapToResident.getLastName());
    }

    @Test
    public void shouldMapToResidentDto(){
        //Given
        Resident resident = createResident();
        //When
        ResidentDto mapToResidentDto = residentMapper.mapToResidentDto(resident);
        //Then
        assertEquals(resident.getId(), mapToResidentDto.getId());
        assertEquals(resident.getFirstName(), mapToResidentDto.getFirstName());
        assertEquals(resident.getLastName(), mapToResidentDto.getLastName());
    }

    @Test
    public void shouldMapToResidentDtoList(){
        //Given
        List<Resident> residentList = new ArrayList<>();
        residentList.add(createResident());
        //When
        List<ResidentDto> mapToResidentDtoList = residentMapper.mapToResidentDtoList(residentList);
        //Then
        assertEquals(1, mapToResidentDtoList.size());
    }
}
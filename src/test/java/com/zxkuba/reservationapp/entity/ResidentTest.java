package com.zxkuba.reservationapp.entity;

import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.repository.ResidentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@SpringBootTest
class ResidentTest {

    @Autowired
    ResidentRepository residentRepository;

    private Resident createResident(){
        return Resident.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .build();
    }

    @Test
    public void shouldFetchAllResident(){
    //Given
        Resident resident1 = createResident();
        Resident resident2 = createResident();
        Resident saveResident1 = residentRepository.save(resident1);
        Resident saveResident2 = residentRepository.save(resident2);
    //When
        List<Resident> residentList = residentRepository.findAll();
    //Then
        Assertions.assertEquals(2, residentList.size());
    //CleanUp
        residentRepository.deleteById(saveResident1.getId());
        residentRepository.deleteById(saveResident2.getId());
    }

    @Test
    public void shouldFetchResident() throws ResidentNotFoundException {
        //Given
        Resident resident = createResident();
        Resident saveResident = residentRepository.save(resident);
        //When
        Resident fetchResident = residentRepository.findById(resident.getId()).orElseThrow(ResidentNotFoundException::new);
        //Then
        Assertions.assertNotNull(fetchResident.getId());
        //CleanUp
        residentRepository.deleteById(saveResident.getId());
    }

    @Test
    public void shouldSaveResident(){
        //Given
        Resident resident = createResident();
        //When
        Resident saveResident = residentRepository.save(resident);
        //Then
        Assertions.assertNotNull(saveResident.getId());
        //CleaUp
        residentRepository.deleteById(saveResident.getId());
    }

    @Test
    public void shouldUpdateResident(){
        //Give
        Resident resident = createResident();
        Resident saveResident = residentRepository.save(resident);
        Long id = saveResident.getId();
        //When
        Resident updateResident = Resident.builder()
                .id(id)
                .firstName("Piotr")
                .lastName("Nowak")
                .build();
        residentRepository.save(updateResident);
        //Then
        Assertions.assertEquals(saveResident.getId(), updateResident.getId());
        Assertions.assertEquals("Piotr", updateResident.getFirstName());
        Assertions.assertEquals("Nowak", updateResident.getLastName());
        //CleanUp
        residentRepository.deleteById(saveResident.getId());
    }

    @Test
    public void shouldDeleteResident(){
        //Given
        Resident resident = createResident();
        Resident saveResident = residentRepository.save(resident);
        //When
        residentRepository.deleteById(saveResident.getId());
        List<Resident> residentList = residentRepository.findAll();
        //Then
        Assertions.assertEquals(0, residentList.size());
    }



}
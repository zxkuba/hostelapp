package com.zxkuba.reservationapp.entity;

import com.zxkuba.reservationapp.exception.CheckOutNotFoundException;
import com.zxkuba.reservationapp.repository.CheckOutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CheckOutTest {

    @Autowired
    CheckOutRepository checkOutRepository;

    private CheckOut createCheckOut(){
        return CheckOut.builder()
                .stayLength(25)
                .totalPrice(new BigDecimal(8000))
                .euroTotalPrice(new BigDecimal(2000))
                .build();
    }

    @Test
    public void shouldFetchAllCheckOuts(){
        //Given
        CheckOut checkOut = createCheckOut();
        CheckOut checkOut1 = createCheckOut();
        CheckOut saveCheckOut = checkOutRepository.save(checkOut);
        CheckOut saveCheckOut1 = checkOutRepository.save(checkOut1);
        //When
        List<CheckOut> checkOutList = checkOutRepository.findAll();
        //Then
        assertEquals(2, checkOutList.size());
        //CleanUp
        checkOutRepository.deleteById(saveCheckOut.getId());
        checkOutRepository.deleteById(saveCheckOut1.getId());
    }

    @Test
    public void shouldFetchCheckOut() throws CheckOutNotFoundException {
        //Given
        CheckOut checkOut = createCheckOut();
        CheckOut saveCheckOut = checkOutRepository.save(checkOut);
        //When
        CheckOut findCheckOut = checkOutRepository.findById(checkOut.getId()).orElseThrow(CheckOutNotFoundException::new);
        //Then
        assertNotNull(findCheckOut.getId());
        //CleanUp
        checkOutRepository.deleteById(saveCheckOut.getId());
    }

    @Test
    public void shouldSaveCheckOut(){
        //Given
        CheckOut checkOut = createCheckOut();
        //When
        CheckOut saveCheckOut = checkOutRepository.save(checkOut);
        //Then
        assertNotNull(saveCheckOut.getId());
        //CleanUp
        checkOutRepository.deleteById(saveCheckOut.getId());
    }

    @Test
    public void shouldUpdateCheckOut(){
        //Given
        CheckOut checkOut = createCheckOut();
        CheckOut saveCheckOut = checkOutRepository.save(checkOut);
        Long id = saveCheckOut.getId();
        //When
        CheckOut updateCheckOut = CheckOut.builder()
                .id(id)
                .stayLength(20)
                .totalPrice(new BigDecimal(4000))
                .euroTotalPrice(new BigDecimal(1000))
                .build();
        checkOutRepository.save(updateCheckOut);
        //Then
        assertEquals(saveCheckOut.getId(), updateCheckOut.getId());
        assertEquals(20, updateCheckOut.getStayLength());
        assertEquals(new BigDecimal(4000), updateCheckOut.getTotalPrice());
        assertEquals(new BigDecimal(1000), updateCheckOut.getEuroTotalPrice());
        //CleanUp
        checkOutRepository.deleteById(saveCheckOut.getId());
    }

    @Test
    public void shouldDeleteCheckOut(){
        //Given
        CheckOut checkOut = createCheckOut();
        CheckOut saveCheckOut = checkOutRepository.save(checkOut);
        //When
        checkOutRepository.deleteById(saveCheckOut.getId());
        List<CheckOut> checkOutList = checkOutRepository.findAll();
        //Then
        assertEquals(0, checkOutList.size());

    }
    
}
package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.domain.CheckOutDto;
import com.zxkuba.reservationapp.entity.CheckOut;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.exception.CheckOutNotFoundException;
import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.mapper.CheckOutMapper;
import com.zxkuba.reservationapp.repository.CheckOutRepository;
import com.zxkuba.reservationapp.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckOutDbService {

    private final CheckOutRepository checkOutRepository;
    private final CheckOutMapper checkOutMapper;
    private final ResidentRepository residentRepository;

    @Autowired
    public CheckOutDbService(CheckOutRepository checkOutRepository, CheckOutMapper checkOutMapper, ResidentRepository residentRepository) {
        this.checkOutRepository = checkOutRepository;
        this.checkOutMapper = checkOutMapper;
        this.residentRepository = residentRepository;
    }

    public List<CheckOutDto> getAllCheckOuts (){
        List<CheckOut> checkOutList = checkOutRepository.findAll();
        return checkOutMapper.mapToCheckOutDtoList(checkOutList);
    }

    public CheckOutDto getCheckOutById(Long checkOutId) throws CheckOutNotFoundException{
        CheckOut checkOut = checkOutRepository.findById(checkOutId).orElseThrow(CheckOutNotFoundException::new);
        return checkOutMapper.mapToCheckOutDto(checkOut);
    }

    public CheckOutDto saveCheckOut(CheckOutDto checkOutDto){
        CheckOut checkOut = checkOutMapper.mapToCheckOut(checkOutDto);
        return checkOutMapper.mapToCheckOutDto(checkOutRepository.save(checkOut));
    }

    public void deleteCheckOut(Long checkOutId){
        checkOutRepository.deleteById(checkOutId);
    }

    public void setResidentToCheckOut(Long checkOutId, Long residentId) throws CheckOutNotFoundException, ResidentNotFoundException{
        CheckOut checkOutResident = checkOutRepository.findById(checkOutId).orElseThrow(CheckOutNotFoundException::new);
        Resident residentCheckOut = residentRepository.findById(residentId).orElseThrow(ResidentNotFoundException::new);

        checkOutResident.setResident(residentCheckOut);
        checkOutRepository.save(checkOutResident);
    }
}

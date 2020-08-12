package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.domain.CheckOutDto;
import com.zxkuba.reservationapp.entity.CheckOut;
import com.zxkuba.reservationapp.entity.Reservation;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.entity.currency.FixerCurrencyRate;
import com.zxkuba.reservationapp.exception.CheckOutNotFoundException;
import com.zxkuba.reservationapp.exception.ReservationNotFoundException;
import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.mapper.CheckOutMapper;
import com.zxkuba.reservationapp.repository.CheckOutRepository;
import com.zxkuba.reservationapp.repository.ReservationRepository;
import com.zxkuba.reservationapp.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Period;
import java.util.List;

@Service
public class CheckOutDbService {

    private final CheckOutRepository checkOutRepository;
    private final CheckOutMapper checkOutMapper;
    private final ReservationRepository reservationRepository;
    private final FixerCurrencyRate fixerCurrencyRate;

    @Autowired
    public CheckOutDbService(CheckOutRepository checkOutRepository, CheckOutMapper checkOutMapper,
                             ReservationRepository reservationRepository, FixerCurrencyRate fixerCurrencyRate) {
        this.checkOutRepository = checkOutRepository;
        this.checkOutMapper = checkOutMapper;
        this.reservationRepository = reservationRepository;
        this.fixerCurrencyRate = fixerCurrencyRate;
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

    public void setReservationToCheckOut(Long checkOutId, Long reservationId)
            throws CheckOutNotFoundException, ReservationNotFoundException {
        CheckOut checkOutReservation = checkOutRepository.findById(checkOutId).orElseThrow(CheckOutNotFoundException::new);
        Reservation reservationCheckOut = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);

        Period period = Period.between(reservationCheckOut.getStayFrom(), reservationCheckOut.getStayTo());
        int stayLength = period.getDays();

        checkOutReservation.setStayLength(stayLength);
        BigDecimal total = reservationCheckOut.getPricePerNight().multiply(BigDecimal.valueOf(stayLength));
        checkOutReservation.setTotalPrice(total);
        checkOutReservation.setEuroTotalPrice(fixerCurrencyRate.getTodayPlnCurrency().multiply(total));

        checkOutReservation.setReservation(reservationCheckOut);
        checkOutRepository.save(checkOutReservation);
    }
}

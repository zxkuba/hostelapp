package com.zxkuba.reservationapp.service;

import com.zxkuba.reservationapp.domain.CheckOutDto;
import com.zxkuba.reservationapp.entity.CheckOut;
import com.zxkuba.reservationapp.entity.Reservation;
import com.zxkuba.reservationapp.entity.Resident;
import com.zxkuba.reservationapp.entity.Room;
import com.zxkuba.reservationapp.entity.currency.FixerCurrencyRate;
import com.zxkuba.reservationapp.exception.CheckOutNotFoundException;
import com.zxkuba.reservationapp.exception.ReservationNotFoundException;
import com.zxkuba.reservationapp.exception.ResidentNotFoundException;
import com.zxkuba.reservationapp.exception.RoomNotFoundException;
import com.zxkuba.reservationapp.mapper.CheckOutMapper;
import com.zxkuba.reservationapp.repository.CheckOutRepository;
import com.zxkuba.reservationapp.repository.ReservationRepository;
import com.zxkuba.reservationapp.repository.ResidentRepository;
import com.zxkuba.reservationapp.repository.RoomRepository;
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
    private final ResidentRepository residentRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public CheckOutDbService(CheckOutRepository checkOutRepository, CheckOutMapper checkOutMapper,
                             ReservationRepository reservationRepository, FixerCurrencyRate fixerCurrencyRate,
                             ResidentRepository residentRepository, RoomRepository roomRepository) {
        this.checkOutRepository = checkOutRepository;
        this.checkOutMapper = checkOutMapper;
        this.reservationRepository = reservationRepository;
        this.fixerCurrencyRate = fixerCurrencyRate;
        this.residentRepository = residentRepository;
        this.roomRepository = roomRepository;
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
        checkOutReservation.setEuroTotalPrice(total.subtract(fixerCurrencyRate.getTodayPlnCurrency()));


        checkOutReservation.setReservation(reservationCheckOut);
        checkOutRepository.save(checkOutReservation);
    }

    public void removeResidentFromRoom(Long roomId, Long residentId) throws RoomNotFoundException, ResidentNotFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
        Resident resident = residentRepository.findById(residentId).orElseThrow(ResidentNotFoundException::new);

            room.getResidents().remove(resident);
            roomRepository.save(room);
    }
}

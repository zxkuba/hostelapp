package com.zxkuba.reservationapp.domain;

import com.zxkuba.reservationapp.entity.Resident;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationDto {

    private Long id;
    private LocalDate stayFrom;
    private LocalDate stayTo;
    private BigDecimal pricePerNight;
    private Resident resident;
}

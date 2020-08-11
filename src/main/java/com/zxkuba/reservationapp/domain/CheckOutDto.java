package com.zxkuba.reservationapp.domain;

import com.zxkuba.reservationapp.entity.Resident;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CheckOutDto {

    private Long id;
    private Resident resident;
    private Integer stayLength;
    private BigDecimal totalPrice;
}

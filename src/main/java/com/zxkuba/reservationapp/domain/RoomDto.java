package com.zxkuba.reservationapp.domain;

import com.zxkuba.reservationapp.entity.Resident;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomDto {

    private Long id;
    private Integer flatNumber;
    private Integer totalQuantityOfBeds;
    private Set<Resident> residents;
    private Integer currentBedsQuantity;


}

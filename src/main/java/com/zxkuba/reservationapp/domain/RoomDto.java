package com.zxkuba.reservationapp.domain;

import com.zxkuba.reservationapp.entity.Resident;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomDto {

    private Long id;
    private Integer flatNumber;
    private Integer totalQuantityOfBeds;
    private List<Resident> residents;
    private Integer currentBedsQuantity;


}

package com.zxkuba.reservationapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Builder
public class RoomDto {

    private Long id;

    private String flatNumber;

    private Integer totalQuantityOfBeds;

    private Integer currentBedsQuantity;
}

package com.zxkuba.reservationapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Builder
@Entity
@Table(name = "ROOMS")
public class Room {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID")
    private Long id;

    @NotNull
    @Column(name = "FLAT_NUMBER")
    private String flatNumber;

    @NotNull
    @Column(name = "TOTAL_QUANTITY_OF_BEDS")
    private Integer totalQuantityOfBeds;

    @Transient
    private Integer currentBedsQuantity;
}

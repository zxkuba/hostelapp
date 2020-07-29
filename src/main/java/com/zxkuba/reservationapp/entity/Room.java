package com.zxkuba.reservationapp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private Integer flatNumber;

    @NotNull
    @Column(name = "TOTAL_QUANTITY_OF_BEDS")
    private Integer totalQuantityOfBeds;


    @OneToMany(
            targetEntity = Resident.class,
            mappedBy = "room",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Resident> residents = new ArrayList<>();

    @Transient
    private Integer currentBedsQuantity;
}

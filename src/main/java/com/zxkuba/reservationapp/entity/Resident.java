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
@Table(name = "RESIDENTS")
public class Resident {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESIDENT_ID")
    private Long id;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "resident",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Reservation> reservations = new ArrayList<>();
}

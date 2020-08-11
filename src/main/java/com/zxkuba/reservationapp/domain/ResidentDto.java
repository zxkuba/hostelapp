package com.zxkuba.reservationapp.domain;

import com.zxkuba.reservationapp.entity.Reservation;
import com.zxkuba.reservationapp.entity.Room;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ResidentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Room room;
    private Set<Reservation> reservations;
}

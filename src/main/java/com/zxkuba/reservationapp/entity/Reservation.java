package com.zxkuba.reservationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @NotNull
    @Column(name = "STAY_FROM")
    private LocalDate stayFrom;

    @NotNull
    @Column(name = "STAY_TO")
    private LocalDate stayTo;

    @NotNull
    @Column(name = "PRICE_PER_NIGHT")
    private BigDecimal pricePerNight;

    @ManyToOne
    @JoinColumn(name = "RESIDENT_ID")
    private Resident resident;

}

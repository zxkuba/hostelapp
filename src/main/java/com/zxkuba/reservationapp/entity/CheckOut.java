package com.zxkuba.reservationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Check_Out")
public class CheckOut {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHECK_OUT_ID")
    private Long id;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "RESIDENT_ID")
    private Resident resident;

    @Column(name = "STAY_LENGTH")
    private Integer stayLength;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

}

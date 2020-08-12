package com.zxkuba.reservationapp.entity.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name= "PLN_RATES")
public class FixerCurrencyRate {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RATE")
    private Long id;

    @Column(name = "PLN_CURRENCY")
    private BigDecimal todayPlnCurrency;
}

package com.zxkuba.reservationapp.domain.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerCurrencyDto {

    private Long id;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("rates")
    private FixerCurrencyRateDto fixerCurrencyRateDto;
}

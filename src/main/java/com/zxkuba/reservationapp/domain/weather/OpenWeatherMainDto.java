package com.zxkuba.reservationapp.domain.weather;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMainDto {

    @JsonProperty("temp")
    private Double temperature;

    @JsonProperty("feels_like")
    private Double feelsTemperature;

    @JsonProperty("temp_min")
    private Double minTemperature;

    @JsonProperty("temp_max")
    private Double maxTemperature;

    @JsonProperty("pressure")
    private Integer pressure;

}

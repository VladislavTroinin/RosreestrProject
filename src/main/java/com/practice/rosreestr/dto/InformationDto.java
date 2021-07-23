package com.practice.rosreestr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationDto {

    private Integer typeNumber;

    private String cadastreNumber;

    private String address;

    private BigDecimal cadastreCost;

}

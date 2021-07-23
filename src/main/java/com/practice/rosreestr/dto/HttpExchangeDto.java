package com.practice.rosreestr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpExchangeDto {

    private Boolean successful;

    private Date timestamp;

    private Long duration;

    private String body;

}

package com.practice.rosreestr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private Date timestamp;

    private String error;

    private String message;

}

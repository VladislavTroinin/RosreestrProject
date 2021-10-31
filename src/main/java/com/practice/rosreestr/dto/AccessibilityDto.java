package com.practice.rosreestr.dto;

import com.practice.rosreestr.model.AccessibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessibilityDto {

    private Date timestamp;

    private String URL;

    private AccessibilityStatus status;

}

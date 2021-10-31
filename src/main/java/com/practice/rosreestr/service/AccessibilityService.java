package com.practice.rosreestr.service;

import com.practice.rosreestr.dto.AccessibilityDto;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;

public interface AccessibilityService {

    AccessibilityDto getAccessibility();

}

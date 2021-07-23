package com.practice.rosreestr.restcontroller;

import com.practice.rosreestr.dto.AccessibilityDto;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;
import com.practice.rosreestr.service.AccessibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accessibility")
public class AccessibilityRestController {

    private final AccessibilityService accessibilityService;

    @GetMapping
    public ResponseEntity<AccessibilityDto> getAccessibility() throws WebServiceNotAccessibleException {
        return new ResponseEntity<>(accessibilityService.getAccessibility(), HttpStatus.OK);
    }

}
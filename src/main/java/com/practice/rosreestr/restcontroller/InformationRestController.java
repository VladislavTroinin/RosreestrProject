package com.practice.rosreestr.restcontroller;

import com.practice.rosreestr.dto.InformationDto;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;
import com.practice.rosreestr.serviceimpl.InformationServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/information")
public class InformationRestController {

    private final InformationServiceImp informationService;

    @GetMapping("/{typeId}/{objectId}")
    public ResponseEntity<InformationDto> getInformation(@PathVariable Integer typeId, @PathVariable String objectId)
            throws WebServiceNotAccessibleException
    {
        return new ResponseEntity<>(informationService.getInformation(typeId, objectId), HttpStatus.OK);
    }

}

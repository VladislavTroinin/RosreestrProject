package com.practice.rosreestr.service;

import com.practice.rosreestr.dto.InformationDto;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;

public interface InformationService {

    InformationDto getInformation(Integer typeId, String objectId) throws WebServiceNotAccessibleException;

}

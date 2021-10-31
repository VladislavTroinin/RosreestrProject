package com.practice.rosreestr.service;

import com.practice.rosreestr.dto.InformationDto;

public interface InformationService {

    InformationDto getInformation(Integer typeId, String objectId);

}

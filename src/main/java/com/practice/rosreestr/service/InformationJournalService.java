package com.practice.rosreestr.service;

import com.practice.rosreestr.dto.PageDto;
import com.practice.rosreestr.entity.Information;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface InformationJournalService {

    Information getInformationById(Long id) throws ResourceNotFoundException;

    Page<Information> getInformationPage(PageDto pageDto);

}

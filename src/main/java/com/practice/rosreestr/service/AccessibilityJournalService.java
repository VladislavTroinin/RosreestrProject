package com.practice.rosreestr.service;

import com.practice.rosreestr.dto.PageDto;
import com.practice.rosreestr.entity.Accessibility;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface AccessibilityJournalService {

    Accessibility getAccessibilityById(Long id) throws ResourceNotFoundException;

    Page<Accessibility> getAccessibilityPage(PageDto pageDto);

}

package com.practice.rosreestr.service;

import com.practice.rosreestr.dto.PageDto;
import com.practice.rosreestr.entity.Accessibility;
import org.springframework.data.domain.Page;

public interface AccessibilityJournalService {

    Accessibility getAccessibilityById(Long id);

    Page<Accessibility> getAccessibilityPage(PageDto pageDto);

}

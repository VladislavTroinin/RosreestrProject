package com.practice.rosreestr.service;

import com.practice.rosreestr.dto.PageDto;
import com.practice.rosreestr.entity.Accessibility;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import com.practice.rosreestr.repository.AccessibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccessibilityJournalServiceImp implements AccessibilityJournalService {

    private final AccessibilityRepository accessibilityRepository;

    public Accessibility getAccessibilityById(Long id) throws ResourceNotFoundException {
        return accessibilityRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Accessibility with id=" + id + " was not found in the journal."));
    }

    public Page<Accessibility> getAccessibilityPage(PageDto pageDto) {
        return accessibilityRepository.findAll(pageDto.getPageable());
    }

}

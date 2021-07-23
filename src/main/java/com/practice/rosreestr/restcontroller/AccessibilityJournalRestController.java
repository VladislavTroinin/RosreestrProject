package com.practice.rosreestr.restcontroller;

import com.practice.rosreestr.dto.PageDto;
import com.practice.rosreestr.entity.Accessibility;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import com.practice.rosreestr.service.AccessibilityJournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/journal/accessibility")
public class AccessibilityJournalRestController {

    private final AccessibilityJournalService accessibilityJournalService;

    @GetMapping("/{id}")
    public ResponseEntity<Accessibility> getAccessibilityById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(accessibilityJournalService.getAccessibilityById(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Accessibility>> getAccessibilityPage(PageDto pageDto) {
        return new ResponseEntity<>(accessibilityJournalService.getAccessibilityPage(pageDto), HttpStatus.OK);
    }

}

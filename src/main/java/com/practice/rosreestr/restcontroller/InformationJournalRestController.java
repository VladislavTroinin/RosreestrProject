package com.practice.rosreestr.restcontroller;

import com.practice.rosreestr.dto.PageDto;
import com.practice.rosreestr.entity.Information;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import com.practice.rosreestr.service.InformationJournalService;
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
@RequestMapping("/journal/information")
public class InformationJournalRestController {

    private final InformationJournalService informationJournalService;

    @GetMapping("/{id}")
    public ResponseEntity<Information> getInformationById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(informationJournalService.getInformationById(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Information>> getInformationPage(PageDto pageDto) {
        return new ResponseEntity<>(informationJournalService.getInformationPage(pageDto), HttpStatus.OK);
    }

}

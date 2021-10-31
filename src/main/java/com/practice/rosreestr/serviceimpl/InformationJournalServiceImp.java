package com.practice.rosreestr.serviceimpl;

import com.practice.rosreestr.dto.PageDto;
import com.practice.rosreestr.entity.Information;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import com.practice.rosreestr.repository.InformationRepository;
import com.practice.rosreestr.service.InformationJournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InformationJournalServiceImp implements InformationJournalService {

    private final InformationRepository informationRepository;

    public Information getInformationById(Long id) {
        return informationRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Information with id=" + id + " was not found in the journal."));
    }

    public Page<Information> getInformationPage(PageDto pageDto) {
        return informationRepository.findAll(pageDto.getPageable());
    }

}

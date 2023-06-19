package com.ceraphi.services.Impl;

import com.ceraphi.dto.GeneralInformationDto;
import com.ceraphi.entities.GeneralInformation;
import com.ceraphi.exceptions.ResourceNotFoundException;
import com.ceraphi.repository.GeneralInformationRepository;
import com.ceraphi.services.Services;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GeneralInformationService implements Services {
    @Autowired
    private GeneralInformationRepository generalInformationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GeneralInformation saveGeneralInformation(GeneralInformationDto generalInformation) {
        GeneralInformation generalInfo = mapToEntity(generalInformation);
        return generalInformationRepository.save(generalInfo);
    }

    @Override
    public GeneralInformation updateGeneralInformation(Long id, GeneralInformationDto updatedGeneralInformation) {
        GeneralInformation GeneralInformation = generalInformationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("generalInfo", "id", id));
        GeneralInformation generalInformation = mapToEntity(updatedGeneralInformation);
        generalInformation.setId(id);
        GeneralInformation.updateDetails(generalInformation);
        return generalInformationRepository.save(generalInformation);
    }


    public GeneralInformation mapToEntity(GeneralInformationDto generalInformationDto) {
        GeneralInformation generalInformation = modelMapper.map(generalInformationDto, GeneralInformation.class);
        return generalInformation;

    }

    public GeneralInformationDto mapToDto(GeneralInformation generalInformation) {
        GeneralInformationDto generalInformationDto = modelMapper.map(generalInformation, GeneralInformationDto.class);
        return generalInformationDto;
    }
}
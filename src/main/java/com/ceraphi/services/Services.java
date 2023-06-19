package com.ceraphi.services;

import com.ceraphi.dto.GeneralInformationDto;
import com.ceraphi.entities.GeneralInformation;

public interface Services {
    public GeneralInformation saveGeneralInformation(GeneralInformationDto generalInfo);
    public GeneralInformation updateGeneralInformation(Long id, GeneralInformationDto updatedGeneralInformation);
}

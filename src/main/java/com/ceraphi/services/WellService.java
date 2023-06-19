package com.ceraphi.services;

import com.ceraphi.dto.WellInfoDto;
import com.ceraphi.entities.WellInformation;

import java.util.List;

public interface WellService {
    WellInfoDto saveWellInformation(Long generalInfo_id, WellInfoDto wellInformation);

    List<WellInfoDto> getWellInformationByGeneralInformationId(long id);
//     void deleteWellDetail(Long wellInformationId, Long wellDetailId);
}

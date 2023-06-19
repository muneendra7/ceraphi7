package com.ceraphi.services.Impl;

import com.ceraphi.dto.WellDto;
import com.ceraphi.dto.WellInfoDto;
import com.ceraphi.entities.GeneralInformation;
import com.ceraphi.entities.WellDetails;
import com.ceraphi.entities.WellInformation;
import com.ceraphi.exceptions.ResourceNotFoundException;
import com.ceraphi.repository.GeneralInformationRepository;
import com.ceraphi.repository.WellDetailsRepo;
import com.ceraphi.repository.WellInformationRepository;
import com.ceraphi.services.WellService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WellInformationService implements WellService {
    @Autowired
    private WellDetailsRepo wellRepository;
    @Autowired
    private WellInformationRepository wellInformationRepository;
    @Autowired
    private GeneralInformationRepository generalInfo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WellInfoDto saveWellInformation(Long id, WellInfoDto wellInformationDto) {
        String wellSiteName = wellInformationDto.getSiteName();
        String coordinates = wellInformationDto.getCoordinates();
        Optional<GeneralInformation> generalInfoById = generalInfo.findById(id);
        GeneralInformation generalInformation = generalInfoById.get();
        WellInformation wellInformation = new WellInformation();
        wellInformation.setGeneralInformation(generalInformation);
        wellInformation.setSiteName(wellSiteName);
        wellInformation.setCoordinates(coordinates);
        for (WellDto wellDetailDto : wellInformationDto.getWells()) {
            WellDetails wellDetail = new WellDetails();
            wellDetail.setWellName(wellDetailDto.getName());
            wellDetail.setWellType(wellDetailDto.getType());
            wellInformation.addWellDetail(wellDetail);
        }
        WellInformation save = wellInformationRepository.save(wellInformation);

        WellInfoDto wellInfoDto = mapToDto(save);
        return wellInfoDto;
    }
//    @Override
//    public WellInfoDto saveWellInformation(Long generalInfo_id, WellInfoDto wellInfo) {
//
//        Optional<GeneralInformation> generalInfoById = generalInfo.findById(generalInfo_id);
//        GeneralInformation generalInformation = generalInfoById.get();
//        WellInformation wellInformation = new WellInformation();
//        wellInformation.setGeneralInformation(generalInformation);
//        wellInformation.setWellName(wellInfo.getWellName());
//        wellInformation.setWellType(wellInfo.getWellType());
//        wellInformation.setCoordinates(wellInfo.getCoordinates());
//        wellInformation.setSiteName(wellInfo.getSiteName());
//        WellInformation save = wellInformationRepository.save(wellInformation);
//        WellInfoDto wellInfoDto = mapToDto(save);
//        return wellInfoDto;
//    }

    @Override
    public List<WellInfoDto> getWellInformationByGeneralInformationId(long id) {
        List<WellInformation> byGeneralInformationId = wellInformationRepository.findByGeneralInformationId(id);
        if (byGeneralInformationId.isEmpty()) {
            throw new ResourceNotFoundException("wellInformation is not present for this ", "id", id);
        } else {

            List<WellInfoDto> collect = byGeneralInformationId.stream().map(s -> (mapToDto(s))).collect(Collectors.toList());

            return collect;
        }
    }

//    @Override
//    public void deleteWellDetail(Long wellInformationId, Long wellDetailId) {
//        // retrieve post entity by id
//        WellInformation wellInformation = wellInformationRepository.findById(wellInformationId).orElseThrow(
//                () -> new ResourceNotFoundException("wellInformation", "id", wellInformationId));
//
//        // retrieve comment by id
//        WellDetails well = wellRepository.findByWellInformationId(wellDetailId).orElseThrow(() ->
//                new ResourceNotFoundException("well", "id", wellDetailId));
//
//        if (!well.getWellInformation().getId().equals(wellInformation.getId())) {
//            throw new ResourceNotFoundException("wellInformation is not present for this ", "id", wellInformationId);
//        }
//
//        wellRepository.delete(well);
//    }











    //    public List<WellInfoDto> getWellDetails(long id) {
//        List<WellInformation> allByGeneralInformation = wellInformationRepository.findByGeneralInformationId(id);
//        List<WellInfoDto> collect = allByGeneralInformation.stream().map(s -> mapToDto(s)).collect(Collectors.toList());
//        return collect;
//    }

















    public WellInfoDto mapToDto(WellInformation wellInformation) {
        WellInfoDto wellInfoDto = modelMapper.map(wellInformation, WellInfoDto.class);
        return wellInfoDto;
    }

    public WellInformation mapToEntity(WellInfoDto wellInfoDto) {
        WellInformation wellInformation = modelMapper.map(wellInfoDto, WellInformation.class);
        return wellInformation;
    }
}
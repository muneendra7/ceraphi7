package com.ceraphi.controller;

import com.ceraphi.dto.WellInfoDto;
import com.ceraphi.entities.WellInformation;
import com.ceraphi.services.Impl.WellInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WellInformationController {
    @Autowired
    private WellInformationService wellInformationService;


    @PostMapping("/{GeneralInfo_Id}/wells")
    public ResponseEntity<Object> saveWellInformation(@PathVariable("GeneralInfo_Id") long GeneralInfo_id,
                                                      @Valid @RequestBody WellInfoDto wellInfo, BindingResult result) {
//        if (result.hasErrors()) {
//            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        WellInfoDto wellInfoDto = wellInformationService.saveWellInformation(GeneralInfo_id, wellInfo);
        return new ResponseEntity<>(wellInfoDto, HttpStatus.CREATED);
    }

    @GetMapping("/general-information/{generalInformationId}")
    public ResponseEntity<List<WellInfoDto>> getWellDetailsByGeneralInformationId(@PathVariable Long generalInformationId) {
        List<WellInfoDto> wellDetails = wellInformationService.getWellInformationByGeneralInformationId(generalInformationId);
        return ResponseEntity.ok(wellDetails);
    }}
//    @DeleteMapping("/{wellInformationId}/well-details/{wellDetailId}")
//    public ResponseEntity<Object> deleteWellDetail(
//            @PathVariable Long wellInformationId,
//            @PathVariable Long wellDetailId
//    ) {
//        try {
//            wellInformationService.deleteWellDetail(wellInformationId, wellDetailId);
//            return ResponseEntity.ok("Well details deleted successfully.");
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//}


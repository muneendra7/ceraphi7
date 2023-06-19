package com.ceraphi.controller;

import com.ceraphi.dto.GeneralInformationDto;
import com.ceraphi.entities.GeneralInformation;
import com.ceraphi.services.Impl.GeneralInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GeneralInformationController {
    @Autowired
    private  GeneralInformationService Services;



    @PostMapping("/generalInfo")
    public ResponseEntity<Object> saveGeneralInformation(@Valid  @RequestBody GeneralInformationDto generalInformation, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        GeneralInformation generalInformation1 = Services.saveGeneralInformation(generalInformation);
        return new ResponseEntity<>(generalInformation1, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGeneralInformation(@PathVariable Long id,@Valid @RequestBody GeneralInformationDto generalInformation,BindingResult result) {
      if(result.hasErrors()) {
          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }


        GeneralInformation updatedGeneralInformation = Services.updateGeneralInformation(id, generalInformation);
//        return ResponseEntity.ok(updatedGeneralInformation);
        return ResponseEntity.status(HttpStatus.CREATED).body("updated the generalInformation successfully");
    }
}
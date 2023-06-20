package com.ceraphi.controller;

import com.ceraphi.dto.ClientDetailsDTO;
import com.ceraphi.entities.ClientDetails;
import com.ceraphi.utils.ClientResponse;
import com.ceraphi.services.ClientDetailsService;
import com.ceraphi.utils.ClientDetailsMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/client-details")
public class ClientDetailsController {

    private final ClientDetailsService clientDetailsService;
    private final ClientDetailsMapper clientDetailsMapper;

    public ClientDetailsController(ClientDetailsService clientDetailsService, ClientDetailsMapper clientDetailsMapper) {
        this.clientDetailsService = clientDetailsService;
        this.clientDetailsMapper = clientDetailsMapper;
    }

    @PostMapping
    public ResponseEntity<Object> saveClientDetails(@Valid @RequestBody ClientDetailsDTO clientDetailsDTO, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ClientDetailsDTO savedClientDetailsDTO = clientDetailsService.saveClientDetails(clientDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClientDetailsDTO);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Object> updateClientDetails(@PathVariable Long clientId, @RequestBody ClientDetailsDTO updatedClientDetailsDTO) {
        ClientDetailsDTO updatedClientDetails = clientDetailsService.updateClientDetails(clientId, updatedClientDetailsDTO);
        return new ResponseEntity<>(updatedClientDetails,HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDetailsDTO> getClientDetails(@PathVariable Long clientId) {
        ClientDetailsDTO clientDetailsDTO = clientDetailsService.getClientDetails(clientId);
        return ResponseEntity.ok(clientDetailsDTO);
    }



    @GetMapping
    public ClientResponse getAllClients( @RequestParam(value = "pageNo", defaultValue = "0", required = false)  int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
      return  clientDetailsService.getAllClients(pageNo, pageSize, sortBy, sortDir);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClientDetails(@PathVariable Long clientId) {
        clientDetailsService.deleteClientDetails(clientId);
//        return ResponseEntity.ok("successfully deleted client with id"+clientId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<ClientDetailsDTO>> searchPosts(@RequestParam(value = "query") String query){
        List<ClientDetailsDTO> clients = clientDetailsService.searchClients(query);
        return ResponseEntity.ok(clients);
    }
}
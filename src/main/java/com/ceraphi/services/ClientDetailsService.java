package com.ceraphi.services;

import com.ceraphi.dto.ClientDetailsDTO;
import com.ceraphi.entities.ClientDetails;
import com.ceraphi.utils.ClientResponse;

import java.util.List;

public interface ClientDetailsService {
    ClientDetailsDTO saveClientDetails(ClientDetailsDTO clientDetailsDTO);
    ClientDetailsDTO updateClientDetails(Long clientId, ClientDetailsDTO updatedClientDetailsDTO);
    ClientDetailsDTO getClientDetails(Long clientId);
    ClientResponse getAllClients(int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteClientDetails(Long clientId);
    List<ClientDetailsDTO> searchClients(String query);
}
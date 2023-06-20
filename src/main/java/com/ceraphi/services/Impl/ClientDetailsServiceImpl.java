package com.ceraphi.services.Impl;

import com.ceraphi.dto.ClientDetailsDTO;
import com.ceraphi.utils.ClientResponse;
import com.ceraphi.entities.ClientDetails;
import com.ceraphi.exceptions.ResourceNotFoundException;
import com.ceraphi.repository.ClientDetailsRepository;
import com.ceraphi.services.ClientDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientDetailsServiceImpl implements ClientDetailsService {
    private final ClientDetailsRepository clientDetailsRepository;
    private final ModelMapper modelMapper;

    public ClientDetailsServiceImpl(ClientDetailsRepository clientDetailsRepository, ModelMapper modelMapper) {
        this.clientDetailsRepository = clientDetailsRepository;
        this.modelMapper = modelMapper;
    }

//
    @Override
    public ClientDetailsDTO saveClientDetails(ClientDetailsDTO clientDetailsDto) {
        String email = clientDetailsDto.getEmail();

        // Check if email already exists
        if (clientDetailsRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Continue with client creation
        ClientDetails clientDetails = modelMapper.map(clientDetailsDto, ClientDetails.class);
        ClientDetails savedClientDetails = clientDetailsRepository.save(clientDetails);
        return modelMapper.map(savedClientDetails, ClientDetailsDTO.class);
    }

    @Override
    public ClientDetailsDTO updateClientDetails(Long clientId, ClientDetailsDTO updatedClientDetailsDTO) {
        ClientDetails existingClientDetails = clientDetailsRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client details not found","id",clientId));

        // Update the existing client details with the new data
        existingClientDetails.setClientName(updatedClientDetailsDTO.getClientName());
        existingClientDetails.setClientType(updatedClientDetailsDTO.getClientType());
        existingClientDetails.setEmail(updatedClientDetailsDTO.getEmail());
        existingClientDetails.setLanguage(updatedClientDetailsDTO.getLanguage());
        existingClientDetails.setAddress(updatedClientDetailsDTO.getAddress());
        existingClientDetails.setCity(updatedClientDetailsDTO.getCity());
        existingClientDetails.setCountry(updatedClientDetailsDTO.getCountry());
        existingClientDetails.setPostalCode(updatedClientDetailsDTO.getPostalCode());
        existingClientDetails.setLocalCurrency(updatedClientDetailsDTO.getLocalCurrency());
        existingClientDetails.setRestrictionDetails(updatedClientDetailsDTO.getRestrictionDetails());
        existingClientDetails.setGeopoliticalData(updatedClientDetailsDTO.getGeopoliticalData());
        existingClientDetails.setRestrictionDetails(updatedClientDetailsDTO.getRestrictionDetails());

        ClientDetails updatedClientDetails = clientDetailsRepository.save(existingClientDetails);
        return modelMapper.map(updatedClientDetails, ClientDetailsDTO.class);
    }

    @Override
    public ClientDetailsDTO getClientDetails(Long clientId) {
        ClientDetails clientDetails = clientDetailsRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client details not found","id",clientId));
        return modelMapper.map(clientDetails, ClientDetailsDTO.class);
    }



    @Override
    public ClientResponse getAllClients(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ClientDetails> client= clientDetailsRepository.findAll(pageable);
        List<ClientDetails> clientDetails = client.getContent();
        List<ClientDetailsDTO> clients = clientDetails.stream()
                .map(clientDetail -> modelMapper.map(clientDetail, ClientDetailsDTO.class))
                .collect(Collectors.toList());
        ClientResponse clientResponse=new ClientResponse();
        clientResponse.setClientDetails(clients);
        clientResponse.setPageNumber(client.getNumber());
        clientResponse.setPageSize(client.getSize());
        clientResponse.setTotalPages(client.getTotalPages());
        clientResponse.setTotalElements(client.getTotalElements());
        clientResponse.setLast(client.isLast());
   return clientResponse;

    }

    @Override
    public void deleteClientDetails(Long clientId) {
        clientDetailsRepository.deleteById(clientId);
    }

    @Override
    public  List<ClientDetailsDTO> searchClients(String query) {
        List<ClientDetails> clientDetails = clientDetailsRepository.searchPosts(query);
        List<ClientDetailsDTO> clients = clientDetails.stream()
                .map(clientDetail -> modelMapper.map(clientDetail, ClientDetailsDTO.class))
                .collect(Collectors.toList());
        return clients;
    }
}
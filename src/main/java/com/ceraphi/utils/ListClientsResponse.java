package com.ceraphi.utils;

import com.ceraphi.dto.ClientDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListClientsResponse {
    private List<ClientDetailsDTO> clients;

    public ListClientsResponse(List<ClientDetailsDTO> clients) {
        this.clients = clients;
    }

    public List<ClientDetailsDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDetailsDTO> clients) {
        this.clients = clients;
    }
}
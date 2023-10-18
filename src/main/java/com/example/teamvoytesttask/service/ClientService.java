package com.example.teamvoytesttask.service;

import com.example.teamvoytesttask.converter.ClientConverter;
import com.example.teamvoytesttask.dto.ClientDto;
import com.example.teamvoytesttask.model.Client;
import com.example.teamvoytesttask.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public ClientService(ClientRepository clientRepository,
                         ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    public ClientDto createClient(ClientDto clientDto){
        Client client = clientConverter.toEntity(clientDto);
        client = clientRepository.save(client);
        return clientConverter.toDto(client);
    }
}

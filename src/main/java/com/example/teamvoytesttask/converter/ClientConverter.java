package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.ClientDto;
import com.example.teamvoytesttask.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends Converter<Client, ClientDto> {
    @Override
    public Client toEntity(ClientDto dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setEmail(dto.getEmail());

        return client;
    }

    @Override
    public ClientDto toDto(Client entity) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(entity.getId());
        clientDto.setEmail(entity.getEmail());

        return clientDto;
    }
}

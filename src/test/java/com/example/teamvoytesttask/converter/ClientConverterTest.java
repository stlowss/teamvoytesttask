package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.ClientDto;
import com.example.teamvoytesttask.model.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientConverterTest {
    private final ClientConverter clientConverter = new ClientConverter();

    @Test
    public void whenConvertClientDtoToEntity_thenEntityHasMatchingFields() {
        ClientDto clientDto = createClientDto();
        Client actualClient = clientConverter.toEntity(clientDto);

        assertEquals(clientDto.getId(), actualClient.getId());
        assertEquals(clientDto.getEmail(), actualClient.getEmail());
    }

    @Test
    public void whenConvertClientToDto_thenDtoHasMatchingFields() {
        Client client = createClient();
        ClientDto actualClientDto = clientConverter.toDto(client);

        assertEquals(client.getId(), actualClientDto.getId());
        assertEquals(client.getEmail(), actualClientDto.getEmail());
    }

    private ClientDto createClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setEmail("test");
        clientDto.setId(1L);
        return clientDto;
    }

    private Client createClient() {
        Client client = new Client();
        client.setEmail("test");
        client.setId(1L);
        return client;
    }
}

package com.ccsw.tutorial.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void findAllShouldReturnAllClients() {
        List<Client> list = new ArrayList<>();
        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());
    }

    public static final Long EXISTS_CATEGORY_ID = 1L;

    public static final String CATEGORY_NAME = "CLIENTE1";

    @Test
    public void saveExistsClientIdShouldUpdate() {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CATEGORY_NAME);

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(client));

        clientService.save(EXISTS_CATEGORY_ID, clientDto);

        verify(clientRepository).save(client);
    }

    @Test
    public void deleteExistsClientIdShouldDelete() throws Exception {

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(client));

        clientService.delete(EXISTS_CATEGORY_ID);

        verify(clientRepository).deleteById(EXISTS_CATEGORY_ID);
    }

    public static final Long NOT_EXISTS_CATEGORY_ID = 0L;

    @Test
    public void getExistsClientIdShouldReturnCategory() {

        Client client = mock(Client.class);
        when(client.getId()).thenReturn(EXISTS_CATEGORY_ID);
        when(clientRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(client));

        Client categoryResponse = clientService.get(EXISTS_CATEGORY_ID);

        assertNotNull(categoryResponse);
        assertEquals(EXISTS_CATEGORY_ID, client.getId());
    }

}

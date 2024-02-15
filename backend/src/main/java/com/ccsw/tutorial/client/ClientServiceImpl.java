package com.ccsw.tutorial.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client get(Long id) {
        // TODO Auto-generated method stub

        return this.clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> findAll() {
        // TODO Auto-generated method stub

        return (List<Client>) this.clientRepository.findAll();
    }

    @Override
    public void save(Long id, ClientDto dto) {
        // TODO Auto-generated method stub
        Client client;

        if (id == null) {
            client = new Client();
        } else {
            client = this.get(id);
        }

        client.setName(dto.getName());

        this.clientRepository.save(client);
    }

    @Override
    public void delete(Long id) throws Exception {
        // TODO Auto-generated method stub

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.clientRepository.deleteById(id);
    }

    @Override
    public Client findById(Long id) throws Exception {
        Optional<Client> a = this.clientRepository.findById(id);

        if (a.isEmpty()) {
            throw new Exception("That id not exists");
        }

        return a.get();
    }
}

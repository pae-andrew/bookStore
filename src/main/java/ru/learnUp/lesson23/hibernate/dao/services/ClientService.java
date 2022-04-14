package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.Client;
import ru.learnUp.lesson23.hibernate.dao.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.getById(id);
    }

    @Transactional
    @CacheEvict(value = "client", key = "#client.id")
    public void update(Client client) {
        clientRepository.save(client);
    }
}

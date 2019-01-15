package com.yador.lab1.controller;

import com.yador.lab1.dao.DeleteException;
import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.dao.converter.EntityDtoConverter;
import com.yador.lab1.dao.converter.impl.ClientJpaDtoConverterImpl;
import com.yador.lab1.dao.impl.jpa.JpaDaoFactory;
import com.yador.lab1.model.dto.Client;
import com.yador.lab1.model.entity.ClientEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateful
public class ClientController {

    @PersistenceContext(unitName = "PitStopResource", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    private GenericDao<Long, ClientEntity> clientDao;
    private EntityDtoConverter<ClientEntity, Client> clientEntityDtoConverter;

    @PostConstruct
    public void init() {
        clientDao = new JpaDaoFactory(entityManager).getClientDao();
        clientEntityDtoConverter = new ClientJpaDtoConverterImpl(clientDao);
    }

    public List<Client> getAll() {
        return clientEntityDtoConverter.allToDto(clientDao.getAll());
    }

    public void editClient(Client client) {
        clientDao.update(clientEntityDtoConverter.toEntity(client));
    }

    public void addClient(Client client) {
        clientDao.add(clientEntityDtoConverter.toEntity(client));
    }

    public void deleteClient(Long id) throws DeleteException {
        clientDao.delete(id);
    }

    public Client buildClient(Long id,
            String firstName,
            String lastName,
            String fatherName,
            String phoneNumber) {
        return new Client()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setFatherName(fatherName)
                .setPhoneNumber(phoneNumber);
    }

    public Client getById(Long id) {
        return clientEntityDtoConverter.toDto(clientDao.get(id));
    }
}

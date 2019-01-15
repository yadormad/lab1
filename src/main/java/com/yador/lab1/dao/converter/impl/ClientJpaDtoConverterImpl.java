package com.yador.lab1.dao.converter.impl;

import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.model.dto.Client;
import com.yador.lab1.model.entity.ClientEntity;

import java.util.HashSet;


public class ClientJpaDtoConverterImpl extends JpaDtoConverter<ClientEntity, Client> {

    private GenericDao<Long, ClientEntity> clientDao;

    public ClientJpaDtoConverterImpl(GenericDao<Long, ClientEntity> clientRepository) {
        this.clientDao = clientRepository;
    }

    //todo builder

    @Override
    public ClientEntity toEntity(Client client) {
        ClientEntity clientEntity = (client.getId() != null) ? clientDao.get(client.getId()) : new ClientEntity();
        clientEntity.setFirstname(client.getFirstName());
        clientEntity.setLastname(client.getLastName());
        clientEntity.setFathername(client.getFatherName());
        clientEntity.setPhone(client.getPhoneNumber());
        clientEntity.setOrderEntitySet(new HashSet<>());
        return clientEntity;
    }

    @Override
    public Client toDto(ClientEntity clientEntity) {
        boolean haveOrders = !clientEntity.getOrderEntitySet().isEmpty();
        return new Client()
                .setId(clientEntity.getId())
                .setFirstName(clientEntity.getFirstname())
                .setLastName(clientEntity.getLastname())
                .setFatherName(clientEntity.getFathername())
                .setPhoneNumber(clientEntity.getPhone())
                .setClientHasOrders(haveOrders);
    }
}

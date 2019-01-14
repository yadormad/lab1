package com.yador.lab1.dao.converter.impl;

import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.model.dto.Client;
import com.yador.lab1.model.entity.ClientEntity;


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
        return clientEntity;
    }

    @Override
    public Client toDto(ClientEntity clientTableEntity) {
        boolean haveOrders = !clientTableEntity.getOrderEntitySet().isEmpty();
        return new Client()
                .setId(clientTableEntity.getId())
                .setFirstName(clientTableEntity.getFirstname())
                .setLastName(clientTableEntity.getLastname())
                .setFatherName(clientTableEntity.getFathername())
                .setPhoneNumber(clientTableEntity.getPhone())
                .setClientHasOrders(haveOrders);
    }
}

package com.yador.lab1.dao.impl.jpa;

import com.yador.lab1.model.entity.ClientEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientJpaDao extends JpaDao<Long, ClientEntity> {

    public ClientJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public ClientEntity get(Long id) {
        return entityManager.find(ClientEntity.class, id);
    }

    @Override
    public List<ClientEntity> getAll() {
        ClientEntity clientEntity = entityManager.find(ClientEntity.class, 2L);
        return entityManager.createQuery("SELECT c FROM ClientEntity c", ClientEntity.class).getResultList();
    }
}

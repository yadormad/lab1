package com.yador.lab1.dao.impl.jpa;

import com.yador.lab1.model.entity.MachinistEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class MachinistJpaDao extends JpaDao<Long, MachinistEntity>{

    public MachinistJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public MachinistEntity get(Long id) {
        return entityManager.find(MachinistEntity.class, id);
    }

    @Override
    public List<MachinistEntity> getAll() {
        return entityManager.createQuery("SELECT c FROM MachinistEntity c", MachinistEntity.class).getResultList();
    }
}

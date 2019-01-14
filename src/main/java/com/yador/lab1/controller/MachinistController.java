package com.yador.lab1.controller;

import com.yador.lab1.dao.DeleteException;
import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.dao.converter.EntityDtoConverter;
import com.yador.lab1.dao.converter.impl.MachinistJpaDtoConverterImpl;
import com.yador.lab1.dao.impl.jpa.JpaDaoFactory;
import com.yador.lab1.model.dto.Machinist;
import com.yador.lab1.model.entity.MachinistEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MachinistController {

    private GenericDao<Long, MachinistEntity> machinistDao;
    private EntityDtoConverter<MachinistEntity, Machinist> machinistEntityDtoConverter;

    @PostConstruct
    public void init() {
        machinistDao = new JpaDaoFactory().getMachinistDao();
        machinistEntityDtoConverter = new MachinistJpaDtoConverterImpl(machinistDao);
    }

    public List<Machinist> getAll() {
        return machinistEntityDtoConverter.allToDto(machinistDao.getAll());
    }

    public void editMachinist(Machinist client) {
        machinistDao.update(machinistEntityDtoConverter.toEntity(client));
    }

    public void addMachinist(Machinist client) {
        machinistDao.add(machinistEntityDtoConverter.toEntity(client));
    }

    public void deleteMachinist(Long id) throws DeleteException {
        machinistDao.delete(id);
    }

    public Machinist buildMachinist() {
        return new Machinist();
    }
}

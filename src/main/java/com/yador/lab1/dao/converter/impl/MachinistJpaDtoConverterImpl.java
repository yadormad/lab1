package com.yador.lab1.dao.converter.impl;

import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.model.dto.Machinist;
import com.yador.lab1.model.entity.MachinistEntity;

public class MachinistJpaDtoConverterImpl extends JpaDtoConverter<MachinistEntity, Machinist> {

    private GenericDao<Long, MachinistEntity> machinistDao;

    public MachinistJpaDtoConverterImpl(GenericDao<Long, MachinistEntity> machinistDao) {
        this.machinistDao = machinistDao;
    }

    @Override
    public MachinistEntity toEntity(Machinist machinist) {
        MachinistEntity machinistEntity = (machinist.getId() != null) ? machinistDao.get(machinist.getId()) : new MachinistEntity();
        machinistEntity.setFirstname(machinist.getFirstName());
        machinistEntity.setLastname(machinist.getLastName());
        machinistEntity.setFathername(machinist.getFatherName());
        machinistEntity.setHourCost(machinist.getValueCost());
        return machinistEntity;
    }

    @Override
    public Machinist toDto(MachinistEntity machinistEntity) {
        return new Machinist()
        .setId(machinistEntity.getId())
        .setFirstName(machinistEntity.getFirstname())
        .setLastName(machinistEntity.getLastname())
        .setFatherName(machinistEntity.getFathername())
        .setValueCost(machinistEntity.getHourCost());
    }
}

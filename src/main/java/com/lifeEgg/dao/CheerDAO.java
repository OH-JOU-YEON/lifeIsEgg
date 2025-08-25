package com.lifeEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.CheerDTO;


@Mapper 
public interface CheerDAO {

public void create(CheerDTO cheer);

    public CheerDTO read(Long id);

    public CheerDTO readByUuid(String uuid);

    public void delete(Long id);


}
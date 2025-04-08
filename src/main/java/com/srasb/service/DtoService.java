package com.srasb.service;

import com.srasb.model.dto.Dto;

import java.util.List;

public interface DtoService <T, S>  {

    public List<T> getDtoList();

    public Dto getDto(S entity);

}

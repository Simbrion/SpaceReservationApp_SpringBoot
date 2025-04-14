package com.srasb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public abstract class DtoServiceWithLogger<T, S> implements DtoService<T, S> {

    protected final DtoService<T, S> dtoService;

    public List<T> getDtoList() {
        return dtoService.getDtoList();
    }

    public T getDto(S entity) {
        return dtoService.getDto(entity);
    }


}

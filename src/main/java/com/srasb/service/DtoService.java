package com.srasb.service;

import java.util.List;

public interface DtoService <T, S>  {

    List<T> getDtoList();

    T getDto(S entity);

}

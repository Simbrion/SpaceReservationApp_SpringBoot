package com.srasb.service;

public interface EntityService<T, S>  {

    T getEntityFromDto(S dto);

    void addEntityBasedOn(S dto);

    void deleteEntityById(int id);

    T getEntityById(int id);

}

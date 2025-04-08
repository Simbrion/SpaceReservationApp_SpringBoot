package com.srasb.service;

public interface EntityService<T, S>  {

    public T getEntityFromDto(S dto);

    public void addEntityBasedOn(S dto);

    public void deleteEntityById(int id);

    public T getEntityById(int id);

}

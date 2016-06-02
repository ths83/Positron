package com.example.boblinux.sqliteplayer;

import java.util.List;

/**
 * Created by boblinux on 01/06/16.
 */
public interface IDAO<genericType>{

    public genericType find(String id) throws Exception;

    public List<genericType> findAll() throws Exception;

    public void persist(genericType t) throws Exception;

    public void remove(genericType t) throws Exception;

    public void update(genericType s, genericType t) throws Exception;

    public void refresh(genericType t) throws Exception;

}
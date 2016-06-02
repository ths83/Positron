package fr.univtln.groupc.dao;

/**
 * Created by mpesnel786 on 02/05/16.
 */
public interface ICrudMethods<T> {
    public T create(T t);
    public T find(Class type, Object id);
    public T update(T t);
    public void delete(Class type,Object id);
}
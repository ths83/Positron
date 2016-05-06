package fr.nmartinez016;

import java.util.List;
import java.util.Map;

/**
 * Created by marti on 05/05/2016.
 */

public interface ICrudService<T> {
    public T create(T t);
    public T find(Class type, Object id);
    public T update(T t);
    public void delete(Class type, Object id);
    public List findWithNameQuery(String queryName, Map parameters);
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);


}

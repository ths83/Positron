package fr.nmartinez016;

/**
 * Created by marti on 05/05/2016.
 */

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * Created by marti on 04/03/2016.
 */

/*
 * Classe générique qui implante l'interface des méthodes crud
 * et des méthodes pour requetes nommées.
 * Cette classe permet d'interagir avec la base de données
 * pour toutes les entités.
 */

public class CCrudServiceBean<T> implements ICrudService<T> {
    EntityManagerFactory mEmf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager mEm = mEmf.createEntityManager();

    public EntityManagerFactory getEmf() {return mEmf;
    }

    public void setEmf(EntityManagerFactory mEmf) {
        this.mEmf = mEmf;
    }

    public EntityManager getEm() {
        return mEm;
    }

    public void setmEm(EntityManager mEm) {
        this.mEm = mEm;
    }

    public T create(T t) {
        EntityTransaction transac = mEm.getTransaction();
        transac.begin();
        mEm.merge(t);
        //mEm.persist(t);
        //mEm.flush();
        //mEm.refresh(t);
        transac.commit();
        return t;
    }

    public T find(Class type, Object id) {
        return (T) mEm.find(type, id);
    }

    public T update(T t) {
        return (T) mEm.merge(t);
    }

    public void delete(Class type, Object id) {
        EntityTransaction transac = mEm.getTransaction();
        transac.begin();
        Object ref = mEm.getReference(type, id);
        mEm.remove(ref);
        transac.commit();
    }

    public List findWithNamedQuery(String queryName){
        return mEm.createNamedQuery(queryName).getResultList();

    }

    public List findWithNameQuery(String queryName, Map parameters) {
        return findWithNamedQuery(queryName, parameters, 0);
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry> rawParameters = parameters.entrySet();
        Query query = this.mEm.createNamedQuery(namedQueryName);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry entry : rawParameters) {
            query.setParameter((String) entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
}

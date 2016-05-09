package fr.univtln.groupc.dao;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mpesnel786 on 02/05/16.
 */

public class CCrudMethods {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();
    public  <T> T create(T t) {
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        this.em.merge(t);
        this.em.flush();
        //this.em.refresh(t);
        transac.commit();
        return t;
    }

    public  <T> T find(Class<T> type,Object id) {
        T t = this.em.find(type, id);
        em.refresh(t);
        //return (T) this.em.find(type, id);
        return t;
    }

    public void delete(Class type,Object id) {
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
        transac.commit();
    }

    public <T> T update(T t){
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.merge(t);
        this.em.flush();
        //this.em.refresh(t);
        transac.commit();
        return t;
    }


    public List findWithNamedQuery(String namedQueryName){
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters, int i) {
        Set<Map.Entry> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if(i > 0)
            query.setMaxResults(i);
        for (Map.Entry entry : rawParameters) {
            query.setParameter((String)entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters){
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }
}
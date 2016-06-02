package fr.univtln.groupc.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marti on 03/05/2016.
 */

/*
 * Classe permettant de mettre les conditions dans les requetes nommees.
 */


public class CQueryParameter {
    private Map parameters = null;

    private CQueryParameter(String name, Object value){
        parameters = new HashMap();
        parameters.put(name, value);
    }

    public static CQueryParameter with(String name, Object value){
        return new CQueryParameter(name, value);
    }

    public CQueryParameter and(String name, Object value){
        parameters.put(name, value);
        return this;
    }

    public Map parameters(){
        return parameters;
    }
}

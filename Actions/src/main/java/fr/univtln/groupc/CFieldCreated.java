package fr.univtln.groupc;

import fr.univtln.groupc.entities.CFieldEntity;

import java.io.Serializable;

/**
 * Created by marti on 01/06/2016.
 */
public class CFieldCreated implements Serializable {

    private CFieldEntity mField;

    public CFieldCreated(){}

    public CFieldCreated(CFieldEntity pField){
        mField = pField;
    }

    public CFieldEntity getField(){
        return mField;
    }

    public void setField(CFieldEntity pField){
        mField = pField;
    }
}

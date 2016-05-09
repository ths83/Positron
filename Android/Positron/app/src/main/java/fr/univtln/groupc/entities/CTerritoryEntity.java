package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpesnel786 on 26/04/16.
 */

public class CTerritoryEntity {

    private int mId;

    private List<CFieldEntity> mFields = new ArrayList<CFieldEntity>();

    public final static String GET_ALL = "Territory.getAll";


    public CTerritoryEntity() {}

    public CTerritoryEntity(CTerritoryBuilder pBuilder){
        mId = pBuilder.mId;
    }

    public static class CTerritoryBuilder{
        private int mId;

        public CTerritoryBuilder(int pId) {
            mId = pId;
        }

        public CTerritoryEntity build(){
            return new CTerritoryEntity((this));
        }

    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public List<CFieldEntity> getFields() {
        return mFields;
    }

    public void addField(CFieldEntity pField){
        mFields.add(pField);
        pField.setTerritory(this);
    }

    public void delFields(CFieldEntity pField){
        mFields.remove(pField);
        pField.setTerritory(null);
    }
}
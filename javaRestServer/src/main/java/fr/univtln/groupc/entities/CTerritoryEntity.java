package fr.univtln.groupc.entities;


import com.owlike.genson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpesnel786 on 26/04/16.
 */

@Entity
@Table(name = "t_territory", schema = "positron")
public class CTerritoryEntity {
    @Id
    @Column(name = "territory_id")
    private int mId;
    @OneToMany(mappedBy="mTerritory")
    @JsonIgnore
    private List<CFieldEntity> mFields = new ArrayList<CFieldEntity>();

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

    @JsonIgnore
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
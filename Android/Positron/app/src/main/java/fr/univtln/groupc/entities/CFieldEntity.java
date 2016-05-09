package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Created by marti on 09/05/2016.
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class CFieldEntity {
    private int mId;
    private CTerritoryEntity mTerritory;
    private List<CLinkEntity> mLinks = new ArrayList<CLinkEntity>();

    public final static String GET_ALL = "Field.getAll";

    public CFieldEntity() {}

    public CFieldEntity(CFieldBuilder pBuilder){
        mId = pBuilder.mId;
    }

    public List<CLinkEntity> getLinks() {
        return mLinks;
    }

    public static class CFieldBuilder{
        private int mId;

        public CFieldBuilder(int pId) {
            mId = pId;
        }

        public CFieldEntity build(){
            return new CFieldEntity((this));
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public CTerritoryEntity getTerritory() {
        return mTerritory;
    }

    public void setTerritory(CTerritoryEntity pTerritory) {
        mTerritory = pTerritory;
    }

    public List<CLinkEntity> getLinks() {
        return mLinks;
    }

    public void setLinks(List<CLinkEntity> pLinks){
        mLinks = pLinks;
    }

    public void addLinks(CLinkEntity pLink){
        mLinks.add(pLink);
        pLink.setField(this);
    }

    public void delLinks(CLinkEntity pLink){
        mLinks.remove(pLink);
        pLink.setField(null);
    }
}

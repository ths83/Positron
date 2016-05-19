package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 09/05/2016.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class CFieldEntity implements Serializable,Comparable<CFieldEntity> {
    private int mId;
    private CTerritoryEntity mTerritory;
    private List<CLinkEntity> mLinks = new ArrayList<CLinkEntity>();

    public final static String GET_ALL = "Field.getAll";

    public CFieldEntity() {}

    public CFieldEntity(CFieldBuilder pBuilder){
        mId = pBuilder.mId;
    }

    public CFieldEntity(CFieldBuilder pBuilder, List<CLinkEntity> pLinks) {
        mId = pBuilder.mId;
        mLinks = pBuilder.mLinks;
    }

    @JsonIgnore
    public List<CLinkEntity> getmLinks() {
        return mLinks;
    }

    public static class CFieldBuilder{
        private int mId;
        private List<CLinkEntity> mLinks = new ArrayList<CLinkEntity>();


        public CFieldBuilder(int pId) {
            mId = pId;
        }

        public CFieldBuilder links(List<CLinkEntity> pLinks){
            mLinks = pLinks;
            return this;
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

    @Override
    public String toString() {
        return "CFieldEntity{" +
                "mId=" + mId +
                /*", mTerritory=" + mTerritory +
                */", mLinks=" + mLinks +
                '}';
    }

    @Override
    public int compareTo(CFieldEntity pFieldCompared) {
        double lSizeOfThis=0,lSizeOfCompared=0;

        for(int li=0;li<3;li++){
            lSizeOfThis=getLinks().get(li).getSize();
            lSizeOfCompared=pFieldCompared.getLinks().get(li).getSize();
        }

        if(lSizeOfThis>lSizeOfCompared){
            return 1;
        }
        else{
            if (lSizeOfThis<lSizeOfCompared){
                return -1;
            }
            else {
                return 0;
            }

        }
    }
}
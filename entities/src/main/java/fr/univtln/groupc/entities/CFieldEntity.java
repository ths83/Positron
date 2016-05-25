package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpesnel786 on 26/04/16.
 */

@Entity
@Table(name = "t_field", schema = "positron")
@NamedQueries(@NamedQuery(name = CFieldEntity.GET_ALL, query = "select f from CFieldEntity f"))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CFieldEntity.class)

public class CFieldEntity implements Serializable,Comparable<CFieldEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "field_id")
    private int mId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "territory_fk")
    private CTerritoryEntity mTerritory;
    @OneToMany(mappedBy="mField",  cascade = CascadeType.MERGE)
    private List<CLinkEntity> mLinks = new ArrayList<CLinkEntity>();

    public final static String GET_ALL = "Field.getAll";

    public CFieldEntity() {}

    public CFieldEntity(CFieldBuilder pBuilder){
        //mId = pBuilder.mId;
        mLinks = pBuilder.mLinks;
        mTerritory = pBuilder.mTerritory;
        for (CLinkEntity lLink : mLinks){
            lLink.setField(this);
        }
    }

    @JsonIgnore
    public List<CLinkEntity> getmLinks() {
        return mLinks;
    }

    public static class CFieldBuilder{
        private int mId;
        private List<CLinkEntity> mLinks = new ArrayList<CLinkEntity>();
        private CTerritoryEntity mTerritory;

        public CFieldBuilder(int pId) {
            mId = pId;
        }

        public CFieldBuilder territory(CTerritoryEntity pTerritory){
            mTerritory = pTerritory;
            return this;
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
                "mId=" + mId/*+
                ", mTerritory=" + mTerritory +
                ", mLinks=" + mLinks */+
                '}';
    }

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
package fr.univtln.groupc.entities;


import com.owlike.genson.annotation.JsonIgnore;
import com.owlike.genson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpesnel786 on 26/04/16.
 */

@Entity
@Table(name = "t_field", schema = "positron")
@NamedQueries(@NamedQuery(name = CFieldEntity.GET_ALL, query = "select f from CFieldEntity f"))
public class CFieldEntity {
    @Id
    @Column(name = "field_id")
    private int mId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "territory_fk")
    @JsonIgnore
    private CTerritoryEntity mTerritory;
    @OneToMany(mappedBy="mField")
    @JsonIgnore
    private List<CLinkEntity> mLinks = new ArrayList<CLinkEntity>();

    public final static String GET_ALL = "Field.getAll";

    public CFieldEntity() {}

    public CFieldEntity(CFieldBuilder pBuilder){
        mId = pBuilder.mId;
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

    @JsonIgnore
    public List<CLinkEntity> getLinks() {
        return mLinks;
    }

    @JsonProperty
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
package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 26/04/16.
 */
@Entity
@Table(name = "t_link" , schema = "positron")
@NamedQueries(@NamedQuery(name = CLinkEntity.GET_ALL, query = "select l from CLinkEntity l"))

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CLinkEntity.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CLinkEntity implements Serializable {
    @Id
    @Column(name = "link_id")
    private int mId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_fk")
    private CFieldEntity mField;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "t_portal_link",joinColumns=@JoinColumn(name="link_fk", referencedColumnName="link_id"),inverseJoinColumns=@JoinColumn(name="portal_fk", referencedColumnName="portal_id"),schema = "positron")
    private List<CPortalEntity> mPortals  = new ArrayList<CPortalEntity>();

    public final static String GET_ALL = "Link.getAll";

    public CLinkEntity() {
        mPortals = new ArrayList<CPortalEntity>();
    }

    public CLinkEntity(CLinkBuilder pBuilder) {
        mId = pBuilder.mId;
        mPortals = pBuilder.mPortals;
        for (CPortalEntity cPortalEntity : mPortals) {
            cPortalEntity.addLink(this);
        }
    }

    @Override
    public String toString() {
        return "CLinkEntity{" +
                "mId=" + mId +
                //", mField=" + mField +
                "; mPortals= " + mPortals +
                +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CLinkEntity that = (CLinkEntity) o;

        if (mId != that.mId) return false;
        if (mField != null ? !mField.equals(that.mField) : that.mField != null) return false;
        return !(mPortals != null ? !mPortals.equals(that.mPortals) : that.mPortals != null);

    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mField != null ? mField.hashCode() : 0);
        result = 31 * result + (mPortals != null ? mPortals.hashCode() : 0);
        return result;
    }

    public static class CLinkBuilder{
        private int mId;
        private List<CPortalEntity> mPortals = new ArrayList<CPortalEntity>();

        public CLinkBuilder(int pId) {
            mId = pId;
        }

        public CLinkBuilder portals(List<CPortalEntity> pPortals){

            mPortals = pPortals;

            return this;
        }

        public CLinkEntity build(){
            return new CLinkEntity((this));
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public List<CPortalEntity> getPortals() {
        return mPortals;
    }

    public void setPortals(List<CPortalEntity> pPortals) {
        mPortals = pPortals;
    }

    public CFieldEntity getField() {
        return mField;
    }


    public void setField(CFieldEntity pField) {
        mField = pField;
    }

    public boolean algoCreateLink(CPortalEntity p1, CPortalEntity p2){
        boolean b=false;
        return(b);
    }





    // Renvoie le carré de la distance

    @JsonIgnore
    public double getSize(){
        double lSize = 0, lLat=0, lLong=0;

        lLat=getPortals().get(1).getLat()-getPortals().get(0).getLat();
        lLong=getPortals().get(1).getLong()-getPortals().get(0).getLong();

        lSize=Math.sqrt(lLat * lLat * lLong * lLong);
        return lSize;
    }


}
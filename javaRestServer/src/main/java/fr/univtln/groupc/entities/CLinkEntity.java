package fr.univtln.groupc.entities;


import com.owlike.genson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 26/04/16.
 */
@Entity
@Table(name = "link" , schema = "positron")
@NamedQueries(@NamedQuery(name = CLinkEntity.GET_ALL, query = "select l from CLinkEntity l"))
public class CLinkEntity implements Serializable {
    @Id
    @Column(name = "link_id")
    private int mId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_fk")
    @JsonIgnore
    private CFieldEntity mField;
    @ManyToMany
    @JoinTable(name = "portal_link",joinColumns=@JoinColumn(name="link_fk", referencedColumnName="link_id"),inverseJoinColumns=@JoinColumn(name="portal_fk", referencedColumnName="portal_id"),schema = "positron")
    @JsonIgnore
    private List<CPortalEntity> mPortals  = new ArrayList<>();

    public final static String GET_ALL = "Link.getAll";

    public CLinkEntity() {}

    public CLinkEntity(CLinkBuilder pBuilder) {
        mId = pBuilder.mId;
        mPortals = pBuilder.mPortals;
        for (CPortalEntity cPortalEntity : mPortals) {
            cPortalEntity.addmLink(this);
        }
    }

    @Override
    public String toString() {
        return "CLinkEntity{" +
                "mId=" + mId +
                ", mField=" + mField +
                ", mPortals=" + mPortals +
                '}';
    }

    public static class CLinkBuilder{
        private int mId;
        private List<CPortalEntity> mPortals = new ArrayList<>();

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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
    @JsonIgnore
    public List<CPortalEntity> getmPortals() {
        return mPortals;
    }

    public void setmPortals(List<CPortalEntity> mPortals) {
        this.mPortals = mPortals;
    }
     public CFieldEntity getmField() {
        return mField;
    }

    public void setmField(CFieldEntity mField) {
        this.mField = mField;
    }

    public boolean algoCreateLink(CPortalEntity p1, CPortalEntity p2){
        boolean b=false;
        return(b);
    }
}
package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by marti on 02/05/2016.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "t_key", schema = "positron")
@NamedQueries({@NamedQuery(name = CKeyEntity.GET_ALL, query = "select k from CKeyEntity k"),@NamedQuery(name = CKeyEntity.GET_BY_PORTAL, query = "select k from CKeyEntity k where k.mPortal=(select p from CPortalEntity p where p.mId=:id )")})

//@JsonDeserialize(as = CKeyEntity.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CKeyEntity.class)

public class CKeyEntity extends AObjectEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "portal_fk")
    private CPortalEntity mPortal;

    public final static String GET_ALL = "Key.getAll";
    public final static String GET_BY_PORTAL = "Key.getByPortal";
    public final static String GET_BY_PLAYER = "Key.getByPlayer";

    public CKeyEntity(){}

    public CKeyEntity(CKeyBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName);
        mPortal = pBuilder.mPortal;
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        mPortal = pPortal;
    }

    @Override
    public String toString() {
        return super.toString() + "CKeyEntity{ mPortal : " + mPortal +"}";
    }

    public static class CKeyBuilder{
        int mId;
        String mName;
        CPortalEntity mPortal;

        public CKeyBuilder(int pId){
            mId = pId;
        }

        public CKeyBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CKeyBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            //TODO KEY
            return this;
        }

        public CKeyEntity build(){
            return new CKeyEntity(this);
        }
    }

}
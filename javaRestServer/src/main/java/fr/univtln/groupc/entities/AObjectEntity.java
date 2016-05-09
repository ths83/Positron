package fr.univtln.groupc.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by arouani277 on 25/04/16.
 */

@Entity
@Table(name = "t_object", schema = "positron")
@NamedQueries(@NamedQuery(name = AObjectEntity.GET_OBJECT_BY_PLAYER, query = "select p.mObjects from CPlayerEntity p where p.mId=:id"))
public abstract class AObjectEntity implements Serializable {
    @Id
    @Column(name = "id")
    private int mId;
    @Column(name = "name")
    private String mName;

    public final static String GET_OBJECT_BY_PLAYER = "Key.getObjectByPlayer";

    @Override
    public String toString() {
        return "CObjectEntity{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }

    public AObjectEntity(){}

    public AObjectEntity(int pId, String pName) {
        mId = pId;
        mName = pName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }
}
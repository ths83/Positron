package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by marti on 09/05/2016.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = CResonatorEntity.class, name = "CResonatorEntity"),
        @JsonSubTypes.Type(value = CTurretEntity.class, name = "CTurretEntity"),
        @JsonSubTypes.Type(value = CConsumableEntity.class, name = "CConsumableEntity"),
        @JsonSubTypes.Type(value = CKeyEntity.class, name = "CKeyEntity")})
public abstract class AObjectEntity {
    private int mId;
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

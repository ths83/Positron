package fr.nmartinez016;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by marti on 04/05/2016.
 */


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CClassB implements Serializable {

    @Id
    private int id;
    private String mName;
    @ManyToOne
    private CClassA mClassA;

    public CClassB() {
    }

    public CClassB(int mId, String mName) {
        this.id = mId;
        this.mName = mName;
    }

    public CClassB(int mId, String mName, CClassA mClassA) {
        this.id = mId;
        this.mName = mName;
        this.mClassA = mClassA;
    }

    public int getId() {
        return id;
    }

    public void setId(int mId) {
        this.id = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public CClassA getmClassA() {
        return mClassA;
    }

    public void setmClassA(CClassA mClassA) {
        this.mClassA = mClassA;
    }

    @Override
    public String toString() {
        return "CClassB{" +
                "mId=" + id +
                ", mName='" + mName + '\'' +
                //", mClassA=" + mClassA +
                '}';
    }
}

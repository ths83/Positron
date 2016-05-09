package fr.nmartinez016;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 04/05/2016.
 */

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CClassA implements Serializable {

    @Id
    private int id;
    private String mName;
    @OneToMany
    private List<CClassB> mClassB = new ArrayList<CClassB>();

    public CClassA() {
    }

    public CClassA(int mId, String mName) {
        this.id = mId;
        this.mName = mName;
    }

    public CClassA(int mId, String mName, List<CClassB> mClassB) {
        this.id = mId;
        this.mName = mName;
        this.mClassB = mClassB;
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

    public List<CClassB> getmClassB() {
        return mClassB;
    }

    public void setmClassB(List<CClassB> mClassB) {
        this.mClassB = mClassB;
    }

    public void addB(CClassB lB){
        mClassB.add(lB);
    }
/*
    @Override
    public String toString() {
        return "CClassA{" +
                "mId=" + id +
                ", mName='" + mName + '\'' +
                ", mClassB=" + mClassB +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CClassA cClassA = (CClassA) o;

        if (id != cClassA.id) return false;
        if (mName != null ? !mName.equals(cClassA.mName) : cClassA.mName != null) return false;
        return !(mClassB != null ? !mClassB.equals(cClassA.mClassB) : cClassA.mClassB != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mClassB != null ? mClassB.hashCode() : 0);
        return result;
    }
}

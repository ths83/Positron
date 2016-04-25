package fr.univtln.bruno.exemple.simplerest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by arouani277 on 25/04/16.
 */
public abstract class CObjectEntity {
    private int mID;
    private String mName;
    private int mLevel;

    @Override
    public String toString() {
        return "CObjectEntity{" +
                "mID=" + mID +
                ", mName='" + mName + '\'' +
                ", mLevel=" + mLevel +
                '}';
    }

    public CObjectEntity(int mID, String mName, int mLevel) {
        this.mID = mID;
        this.mName = mName;
        this.mLevel = mLevel;
    }
}

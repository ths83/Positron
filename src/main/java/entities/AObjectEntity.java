package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by arouani277 on 25/04/16.
 */

@Entity
@Table(name = "t_object")
public abstract class AObjectEntity {
    @Id
    @Column(name = "id")
    private int mID;
    @Column(name = "name")
    private String mName;

    @Override
    public String toString() {
        return "CObjectEntity{" +
                "mID=" + mID +
                ", mName='" + mName + '\'' +
                '}';
    }

    public AObjectEntity(int pID, String pName) {
        mID = pID;
        mName = pName;
    }
}

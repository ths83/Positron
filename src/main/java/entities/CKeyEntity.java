package entities;

import javax.persistence.*;

/**
 * Created by marti on 02/05/2016.
 */

@Entity
@Table(name = "t_key")
public class CKeyEntity extends AObjectEntity {
    @ManyToOne
    @Column(name = "portal_fk")
    private CPortalEntity mPortal;

    public CKeyEntity(CKeyBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName);
        mPortal = pBuilder.mPortal;
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
            return this;
        }

        public CKeyEntity build(){
            return new CKeyEntity(this);
        }
    }


}

package fr.univtln.groupc;

import fr.univtln.groupc.entities.CLinkEntity;

import java.io.Serializable;

/**
 * Created by marti on 31/05/2016.
 */
public class CCreateLink implements Serializable {

    private CLinkEntity mLink;

    public CCreateLink(){}

    public CCreateLink(CLinkEntity pLink){
        mLink = pLink;
    }

    public void setLink(CLinkEntity pLink){
        mLink = pLink;
    }

    public CLinkEntity getLink(){
        return mLink;
    }

    @Override
    public String toString() {
        return "CCreateLink{" +
                "mLink=" + mLink +
                '}';
    }
}

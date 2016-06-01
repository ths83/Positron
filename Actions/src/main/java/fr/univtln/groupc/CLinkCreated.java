package fr.univtln.groupc;


import fr.univtln.groupc.entities.CLinkEntity;

import java.io.Serializable;


/**
 * Created by marti on 01/06/2016.
 */
public class CLinkCreated implements Serializable{

    private CLinkEntity mLink;

    public CLinkCreated(){}

    public CLinkCreated(CLinkEntity pLink){
        mLink = pLink;
    }

    public void setLink(CLinkEntity pLink){
        mLink = pLink;
    }

    public CLinkEntity getLink(){
        return mLink;
    }
}

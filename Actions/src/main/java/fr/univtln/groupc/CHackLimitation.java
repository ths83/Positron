package fr.univtln.groupc;

import java.io.Serializable;

/**
 * Created by marti on 09/06/2016.
 */
public class CHackLimitation implements Serializable {
    private String mDuration;

    public CHackLimitation(){}

    public CHackLimitation(String pDuration){
        mDuration = pDuration;
    }

    public String getDuration(){
        return mDuration;
    }

    public void setDuration(String pDuration){
        mDuration = pDuration;
    }
}

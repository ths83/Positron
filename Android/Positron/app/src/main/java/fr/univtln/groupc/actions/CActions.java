package fr.univtln.groupc.actions;

import android.util.Log;

import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

/**
 * Created by xdurbec066 on 17/05/16.
 */
public class CActions {

    public void buildResonator (CPortalEntity pPortal,CResonatorEntity pResonator) {

        if (pPortal.getResonators().size()>=8){
            pPortal.addResonator(pResonator);
            // Modifier le portail de pResonator en pPortal

            pPortal.attributeTeam();


        }
        else{
            Log.d("BuildResonator","Plus de place sur le portail / Portal Overload");
        }


    }
}

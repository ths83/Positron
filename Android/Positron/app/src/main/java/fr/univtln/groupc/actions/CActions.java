package fr.univtln.groupc.actions;

import android.util.Log;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

/**
 * Created by xdurbec066 on 17/05/16.
 */
public class CActions {

    public CPortalEntity buildResonator (CPortalEntity pPortal,CResonatorEntity pResonator) {

        if (pPortal.getResonators().size() >= 8){
            pPortal.addResonator(pResonator);
        }
        else{
            Log.d("BuildResonator","Plus de place sur le portail / Portal Overload");
        }
        return pPortal;

    }

    public void attackBuilding (CConsumableEntity pAmmunition, ABuildingEntity pBuilding , CPlayerEntity pPlayer){
        int lDammage = 0;

        if(pAmmunition.getName() == "Attack"){
            pPlayer.attack(pBuilding,lDammage);
        }

        else{
            Log.d("attackBuilding", "Consommable non approrié");
        }

    }


}

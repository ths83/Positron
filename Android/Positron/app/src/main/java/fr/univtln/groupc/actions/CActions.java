package fr.univtln.groupc.actions;

import android.util.Log;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.IFighter;
import fr.univtln.groupc.entities.ITarget;
import fr.univtln.groupc.rest.CRestUpdate;

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
/*
    public ABuildingEntity attackBuilding (CConsumableEntity pAmmunition, ABuildingEntity pBuilding , CPlayerEntity pPlayer){
        int lDammage = 0;

        if(pAmmunition.getName() == "Attack"){

                switch (pAmmunition.getRarity()){
                    case(0):
                        lDammage = pPlayer.getLevel() * 10 + 20;
                        break;

                    case(1):
                        lDammage = pPlayer.getLevel() * 15 + 30;
                        break;

                    case(2):
                        lDammage = pPlayer.getLevel() * 20 + 40;
                        break;
                }
            pBuilding = (ABuildingEntity) pPlayer.attack(pBuilding,lDammage);
        }

        else{
            Log.d("attackBuilding", "Consommable non approrié");
        }
        return pBuilding;
    }
*/

}

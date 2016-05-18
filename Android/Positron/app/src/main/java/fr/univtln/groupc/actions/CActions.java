package fr.univtln.groupc.actions;

import android.util.Log;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.IFighter;

/**
 * Created by xdurbec066 on 17/05/16.
 */
public class CActions {

    public void buildResonator (CPortalEntity pPortal,CResonatorEntity pResonator) {

        if (pPortal.getResonators().size() >= 8){
            pPortal.addResonator(pResonator);
            // Modifier le portail de pResonator en "pPortal" par cascade

            pPortal.attributeTeam();

            // Envoyer le changement par REST
        }
        else{
            Log.d("BuildResonator","Plus de place sur le portail / Portal Overload");
        }


    }

    public void attackBuilding (CConsumableEntity pAmmunition,ABuildingEntity pBuilding , CPlayerEntity pPlayer){
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
            pPlayer.attack(pBuilding,lDammage);
        }

        else{
            Log.d("attackBuilding", "Consommable non approrié");
        }

    }


}

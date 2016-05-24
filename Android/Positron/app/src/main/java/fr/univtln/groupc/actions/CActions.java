package fr.univtln.groupc.actions;

import android.util.Log;

import java.util.Objects;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CShieldEntity;
import fr.univtln.groupc.entities.CTurretEntity;

/**
 * Created by xdurbec066 on 17/05/16.
 */
public class CActions {

    public CPortalEntity buildResonator (CPortalEntity pPortal,CResonatorEntity pResonator) {

        if (pPortal.getResonators().size() >= 8 && pResonator.getOwner().getLevel() >= pResonator.getLevel()){
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
            pPlayer.attack(pBuilding, pAmmunition);
        }

        else{
            Log.d("attackBuilding", "Consommable non approrié");
        }

    }


    public void piratage(CPortalEntity pPortal, CPlayerEntity pPlayer){

        if(pPortal.getTeam() != null){
            if(pPortal.getTeam() == pPlayer.getTeam()){

            }
            else{

            }

        }
        else{

        }
        
    }

    public AObjectEntity createObject(int pTypeObjet , int pPortalLevel){
      int lLevelObject = 0;
      int lRarety = 0;

       // TODO Calcule lLevelObject & lRarety

     switch (pTypeObjet){

         case(0):{
                return (AObjectEntity) new CResonatorEntity.CResonatorBuilder(10).energyMax(lLevelObject*20).energy(lLevelObject*20).level(lLevelObject).build();
         }
         case(1): {
             return (AObjectEntity) new CTurretEntity.CTurretBuilder(10).energy(lLevelObject * 50).energyMax(lRarety * 50).damage(10 * lRarety).build();
         }
         case(2):{
             return (AObjectEntity) new CShieldEntity.CShieldBuilder(10).energy(lLevelObject * 50).energyMax(lRarety * 50).damage(10*lRarety).build();
          }

     }
        return null;
    }
}

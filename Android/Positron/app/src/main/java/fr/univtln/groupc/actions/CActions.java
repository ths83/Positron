package fr.univtln.groupc.actions;

import android.util.Log;

import java.util.List;
import java.util.Objects;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CShieldEntity;
import fr.univtln.groupc.entities.CTurretEntity;

/**
 * Created by xdurbec066 on 17/05/16.
 */
public class CActions {

    public CPortalEntity buildResonator(CPortalEntity pPortal, CResonatorEntity pResonator) {

        if (pPortal.getResonators().size() < 8 ) {
            if ( pResonator.getOwner().getLevel() >= pResonator.getLevel()) {


                pPortal.addResonator(pResonator);
                //TODO add XP
            }
            else{
                //   Log.d("BuildResonator","Niveau pas assez élever pour poser ce portail");
                System.out.println("Niveau pas assez élever");
            }
        }
        else {
            //Log.d("BuildResonator", "Plus de place sur le portail / Portal Overload");
            System.out.println("Plus de place sur le portail.");
        }
        return pPortal;

    }

/////////////////////////////////////////////////////////////

    public void attackBuilding(CConsumableEntity pAmmunition, ABuildingEntity pBuilding, CPlayerEntity pPlayer) {
        int lDammage = 0;

        if (pAmmunition.getName() == "Attack") {
            pPlayer.attack(pBuilding, pAmmunition);
            //TODO add XP
        } else {
            //Log.d("attackBuilding", "Consommable non approrié");
            System.out.println("Consommable non approrié");
        }

    }



    /////////////////////////////////////////////////////////////////////

    public AObjectEntity createObject(int pTypeObjet, int pLevelObject, int pRarety) {

        switch (pTypeObjet) {

            case (0): {
                return (AObjectEntity) new CResonatorEntity.CResonatorBuilder(10).energyMax(pLevelObject * 20).energy(pLevelObject * 20).level(pLevelObject).build();
            }
            case (1): {
                return (AObjectEntity) new CTurretEntity.CTurretBuilder(10).energy(pLevelObject * 50).energyMax(pLevelObject * 50).damage(10 * pLevelObject).build();
            }
            case (2): {
                return (AObjectEntity) new CShieldEntity.CShieldBuilder(10).level(pLevelObject).energy(pLevelObject * 50).energyMax(pRarety * 50).defensBonus(10 * pRarety).build();
            }
            case (3): {
                return (CConsumableEntity) new CConsumableEntity.CConsumableBuilder(10).name("Attack").rarity(pRarety).build();
            }


        }
        return null;
    }

    public int calculLevel(int pPortalLevel, int pPlayerLevel) {
        int lLevel = 0;
        // Niveau Max
        if (pPlayerLevel == 8 && pPortalLevel == 8) {
            lLevel = 8;
        }
        else {
            int lMinima =0;
            // On garde la valeur la plus basse
            if(pPlayerLevel <= pPortalLevel){
                lMinima = pPlayerLevel;
            }
            else{
                lMinima = pPortalLevel;
            }


            if(lMinima <= 2){
                // Pour 1
                if(lMinima == 1) {
                    lLevel = 1 + (int) (Math.random() * 2);
                }
                // Pour 2
                else {
                    lLevel = 1 + (int) (Math.random() * 3);
                }
            }
            else{
                // Pour 7
                if (lMinima ==7){
                    lLevel = 5 + (int)(Math.random() * 3);
                }
                // Normal
                else{
                    lLevel = (lMinima -2 ) + ((int)(Math.random()*5));
                }
            }
        }

        return lLevel;
    }

    public int calculRarety(int pPortalLevel) {
        double lRandom = (int) (Math.random() * (100));
        //  System.out.println(lRandom);
        switch (pPortalLevel) {


            // Return rarety Max

            case 8:
                return 3;


            case 7:
                if (lRandom > 90) {
                    return 3;
                } else {
                    if (lRandom > 20) {
                        return 2;
                    } else {
                        return 1;
                    }
                }


            case 6:
                if (lRandom == 95) {
                    return 3;
                } else {
                    if (lRandom > 40) {
                        return 2;
                    }
                    else {
                        return 1;
                    }
                }

            case 5:
                if (lRandom == 100) {
                    return 3;
                }
                else {
                    if (lRandom > 60) {
                        return 2;
                    }
                    else {
                        return 1;
                    }
                }


            case 4:
                if (lRandom > 80) {
                    return 2;
                }
                else {
                    if (lRandom > 40) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }

            case 3:
                if (lRandom > 60) {
                    return 1;
                }
                else {
                    return 0;
                }

            case 2:
                if (lRandom > 80) {
                    return 1;
                }
                else {
                    return 0;
                }

            case 1:
                return 0;
        }

        return 0;
    }


    /////////////////////////////////////////////////////////////////////

    public int calculTypeObject(){
        int lType = 0, lRandom=0;
        lRandom = (int)(Math.random() * (100));

        if(lRandom > 90){
            lType = 0;
        }
        else if(60 < lRandom && lRandom <= 90){
            lType = 1;
        }
        else if(40 < lRandom && lRandom <= 60){
            lType = 2;
        }
        else if(lRandom <= 40){
            lType = 3;
        }

        return lType;
    }

    /////////////////////////////////////////////////////////////////////

    // TODO : Choirsie/Vérifier sens de création: Cascade Player -> Object or Object -> Player
    public CKeyEntity keyHacking(CPortalEntity pPortal){
        return new CKeyEntity.CKeyBuilder(140).portal(pPortal).build();
    }
//////////////////////////////////////////////////////////////////////////////////////////

    public void bombeExplosion(CPortalEntity pPortal, int pDamage){
        List<CResonatorEntity> lResonatorListe = pPortal.getResonators();
        List<ABuildingEntity> lBuildingListe = pPortal.getBuildings();

        for(CResonatorEntity lResonator : lResonatorListe){
            lBuildingListe.add((ABuildingEntity) lResonator);
        }
        for(ABuildingEntity lBuilding : lBuildingListe){
            lBuilding.takeDamage(null,pDamage);

        }
    }
    //////////////////////////////////////////////////////////////////////////////////////
/*
    public CLinkEntity createLink(CKeyEntity pKey, CPlayerEntity pPortal){

        CLinkEntity
    }
    */



}

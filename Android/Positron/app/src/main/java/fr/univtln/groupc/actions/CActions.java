package fr.univtln.groupc.actions;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CShieldEntity;
import fr.univtln.groupc.entities.CTurretEntity;
import fr.univtln.groupc.rest.CRestGet;
import fr.univtln.groupc.rest.CRestUpdate;

/**
 * Created by xdurbec066 on 17/05/16.
 */
public class CActions {

    public static CPortalEntity buildResonator(CPortalEntity pPortal, CResonatorEntity pResonator) {

        if (pPortal.getResonators().size() < 8 ) {
            if ( pResonator.getOwner().getLevel() >= pResonator.getLevel()) {


                pPortal.addResonator(pResonator);
                if (pResonator.getPortal() != null){
                    pResonator.getOwner().addXP(pResonator.getLevel()*10);
                }
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

public static CPortalEntity buildBuilding(CPortalEntity pPortal, ABuildingEntity pBuilding , CPlayerEntity pPlayer) {


    if (pPortal.getBuildings().size() < 4 ) {
        if ( pPlayer.getLevel() >= pBuilding.getLevel() ) {
            if (pBuilding instanceof CShieldEntity && pBuilding.getLevel() >2) {
                if(pPlayer.havingSkill(11)){
                    pPortal.addBuilding(pBuilding);
                    if (pBuilding.getPortal() != null) {
                        pPlayer.addXP(pBuilding.getLevel() * 20);
                    }
                }
                else{
                    System.out.println("Compétence non acquise");
                }
            } else {
                pPortal.addBuilding(pBuilding);
                if (pBuilding.getPortal() != null) {
                    pPlayer.addXP(pBuilding.getLevel() * 20);
                }
            }
        }
        else {
            //   Log.d("BuildResonator","Niveau pas assez élever pour poser ce portail");
            System.out.println( "Niveau pas assez élever" );
        }
    }
    else {
        //Log.d("BuildResonator", "Plus de place sur le portail / Portal Overload");
        System.out.println( "Plus de place sur le portail." );
    }
    return pPortal;

}

/////////////////////////////////////////////////////////////

    public void attackBuilding(CConsumableEntity pAmmunition, ABuildingEntity pBuilding, CPlayerEntity pPlayer) {
        int lBuildingEnergy = pBuilding.getEnergy();

        if (pAmmunition.getName().equals("Attack")) {
            if(pAmmunition.getRarity() < 2) {
                pPlayer.attack(pBuilding, pAmmunition);
            }
            else{
                if ( (pAmmunition.getRarity() == 2 && pPlayer.havingSkill(21)) || ((pAmmunition.getRarity() == 2 && pPlayer.havingSkill(222))) ){
                    pPlayer.attack(pBuilding, pAmmunition);
                }
                else{
                    System.out.println("Skill Required");
                }
            }

                if(pBuilding.getEnergy()<lBuildingEnergy){
                pPlayer.addXP((lBuildingEnergy - pBuilding.getEnergy()) * 10);
            }
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
            case (4): {
                return (CConsumableEntity) new CConsumableEntity.CConsumableBuilder(10).name("Bombe").rarity(pRarety).build();
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
        CRestUpdate lUpdate = new CRestUpdate();
        for(CResonatorEntity lResonator : lResonatorListe){
            lBuildingListe.add((ABuildingEntity) lResonator);
        }
        for(ABuildingEntity lBuilding : lBuildingListe){
            lBuilding.takeDamage(null, pDamage);
            Log.d("test5", "-->" + lBuilding.getEnergy());
            //lUpdate.updateBuildingRest(lBuilding);
        }

        lUpdate.updatePortalRest(pPortal);
        //return pPortal;
    }
    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creer un lien
     * @param pPortal
     * @param pKey
     * @return
     */
    public CLinkEntity createLink(CKeyEntity pKey, CPortalEntity pPortal){
        if(pKey.getPortal().getTeam() == pPortal.getTeam()) {
            List<CPortalEntity> lPortalListe = new ArrayList<>();
            lPortalListe.add(pPortal);
            lPortalListe.add(pKey.getPortal());

            return new CLinkEntity.CLinkBuilder(1).portals(lPortalListe).build();
        }
        else{
            return null;
        }
    }



    public Boolean portalAllied(CPortalEntity pPortal,CPlayerEntity pPlayer){
        /*if(pPlayer.getTeam() == pPortal.getTeam()){
            return true;
        }ddu
        else{
            return false;
        }
        */
        return pPlayer.getTeam() == pPortal.getTeam();
    }





}

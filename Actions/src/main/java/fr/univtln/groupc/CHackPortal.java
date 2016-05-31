package fr.univtln.groupc;

import fr.univtln.groupc.entities.*;

import java.io.Serializable;

/**
 * Created by marti on 27/05/2016.
 */
public class CHackPortal implements Serializable {

    private CPlayerEntity mPlayer;
    private CPortalEntity mPortal;

    public CHackPortal(){}

    public CHackPortal(CHackPortalBuilder pBuilder){
        mPlayer = pBuilder.mPlayer;
        mPortal = pBuilder.mPortal;
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }

    public void setPortal(CPortalEntity pPortal){
        mPortal = pPortal;
    }

    public CPortalEntity getPortal(){
        return mPortal;
    }

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


    public void hacking(CPlayerEntity pPlayer, CPlayerEntity pPortal){
        int li =0;
        // TODO XP ++
        if(pPortal.getTeam() == null){
            for(li=0;li<5;li++){
                pPlayer.addObjects(createObject(calculTypeObject(), calculLevel(pPortal.getLevel(), pPlayer.getLevel()), calculRarety(pPortal.getLevel())));
            }
        }
        else{
            if(pPortal.getTeam() == pPlayer.getTeam()){
                for(li=0;li<10;li++){
                    pPlayer.addObjects(createObject(calculTypeObject(), calculLevel(pPortal.getLevel(), pPlayer.getLevel()), calculRarety(pPortal.getLevel())));

                }
            }
            else {
                //TODO XP ++
                for(li=0;li<5;li++){
                    pPlayer.addObjects(createObject(calculTypeObject(), calculLevel(pPortal.getLevel(), pPlayer.getLevel()), calculRarety(pPortal.getLevel())));

                }
            }
        }

    }

    public static class CHackPortalBuilder{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CHackPortalBuilder(){}

        public CHackPortalBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CHackPortalBuilder player(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CHackPortal build(){
            return new CHackPortal(this);
        }
    }


}

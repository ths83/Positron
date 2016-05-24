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

    public CPortalEntity buildResonator(CPortalEntity pPortal, CResonatorEntity pResonator) {

        if (pPortal.getResonators().size() >= 8 && pResonator.getOwner().getLevel() >= pResonator.getLevel()) {
            pPortal.addResonator(pResonator);
        } else {
            Log.d("BuildResonator", "Plus de place sur le portail / Portal Overload");
        }
        return pPortal;

    }


    public void attackBuilding(CConsumableEntity pAmmunition, ABuildingEntity pBuilding, CPlayerEntity pPlayer) {
        int lDammage = 0;

        if (pAmmunition.getName() == "Attack") {
            pPlayer.attack(pBuilding, pAmmunition);
        } else {
            Log.d("attackBuilding", "Consommable non approrié");
        }

    }


    public void piratage(CPortalEntity pPortal, CPlayerEntity pPlayer) {

        if (pPortal.getTeam() != null) {
            if (pPortal.getTeam() == pPlayer.getTeam()) {

            } else {

            }

        } else {

        }

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
        } else {
            //Cas Basic (+-2 possible)
            if (pPlayerLevel + 2 <= pPortalLevel && pPlayerLevel - 2 >= 1) {
                lLevel = (pPlayerLevel - 2) + (int) (Math.random() * ((pPlayerLevel + 2)) - (pPlayerLevel - 2));
            } else {
                if (pPlayerLevel == pPortalLevel) {
                    // Portail & Player lvl 1
                    if (pPlayerLevel == 1) {
                        lLevel = (pPlayerLevel) + (int) (Math.random() * ((pPlayerLevel + 2)) - (pPlayerLevel));
                    }
                    // Portail & Player same level between 2 et 7
                    else {
                        lLevel = (pPlayerLevel - 2) + (int) (Math.random() * (pPlayerLevel) - (pPlayerLevel - 2));
                    }
                } else {
                    // Player = 1  & Portail = 2
                    if (pPlayerLevel == 1 && pPortalLevel == 2) {
                        lLevel = (pPlayerLevel) + (int) (Math.random() * ((pPlayerLevel + 1)) - (pPlayerLevel));
                    }
                    // Player = Portail - 1
                    else {
                        lLevel = (pPlayerLevel - 2) + (int) (Math.random() * ((pPlayerLevel + 1)) - (pPlayerLevel - 2));
                    }

                }
            }
        }
        return lLevel;
    }

    public int calculRarety(int pPortalLevel) {
        int lRandom = (int) Math.random() * (100);

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


}

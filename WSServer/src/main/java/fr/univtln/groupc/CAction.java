package fr.univtln.groupc;


import fr.univtln.groupc.entities.*;

/**
 * Created by marti on 31/05/2016.
 */
public class CAction {

    /*
     * Ajoute le resonateur au portail
     * et augmente l'xp du joueur l'ayant posé.
     *
     * ---------------
     *
     * Add the resonator to the portal
     * and increases the player xp.
     */
/*
    public static CPoseResornator attachResonatorToPortal(CPoseResonator pPoseResonator){
        System.out.println("salut attach 1");
        pPoseResonator.getmResonatorId()
        if (pPoseResonator.getPortal().getResonators().size() < 8){
            System.out.println("salut attach 2");
            // todo : demander a xavier d expliquer ce if
            if (pPoseResonator.getResonator().getOwner().getLevel() >= pPoseResonator.getResonator().getLevel()){
                System.out.println("salut attach 3");
                pPoseResonator.getPortal().addResonator(pPoseResonator.getResonator());
                System.out.println("nb reso au portail : " + pPoseResonator.getPortal().getResonators().size());
                System.out.println("getportal null ? " + pPoseResonator.getPortal() == null);
                pPoseResonator.getPortal().attributeTeam();
                pPoseResonator.getPlayer().removeObject(pPoseResonator.getResonator());
                // todo : add xp to player
            }
            else{
                System.out.println("Niveau pas assez eleve");
            }
        }
        else {
            System.out.println("Plus de place sur le portail");
        }
        System.out.println("team dans attach : " + pPoseResonator.getPortal().getTeam());
        return pPoseResonator;
    }*/


    public static CPortalEntity attachResonatorToPortal(CPortalEntity pPortal, CResonatorEntity pResonator){
        System.out.println("salut attach 1");
        if (pPortal.getResonators().size() < 8){
            System.out.println("salut attach 2");
            // todo : demander a xavier d expliquer ce if
            System.out.println("owner null ? " + pResonator.getOwner() == null);
            System.out.println("resonator null ? " + pResonator == null);
            System.out.println("owner level null ? " + pResonator.getOwner().getLevel() == null);
            System.out.println("resonator level null ? " + pResonator.getLevel() == null);
            if (pResonator.getOwner().getLevel() >= pResonator.getLevel()){
                System.out.println("salut attach 3");
                pPortal.addResonator(pResonator);

                pPortal.attributeTeam();
                pResonator.getOwner().removeObject(pResonator);
                // todo : add xp to player
            }
            else{
                System.out.println("Niveau pas assez eleve");
            }
        }
        else {
            System.out.println("Plus de place sur le portail");
        }
        System.out.println("team dans attach : " + pPortal.getTeam());
        return pPortal;
    }

    /*
     * Appelle la methode qui attache un resonateur a un portail
     * Retourne un vrai si la team a change, false sinon
     *
     * -----------
     *
     * Calls the method that attaches a resonator
     * to the portal.
     * Returns true if the resonator's team changed,
     * false otherwise.
     */
/*
    public static Boolean isTeamChangedAfterResonatorPoseOnPortal(CPoseResonator pPoseResonator){
        System.out.println("dans la methode");
        int lPreviousId = 0;
        if (pPoseResonator.getPortal().getTeam() != null){
            lPreviousId = pPoseResonator.getPortal().getTeam().getId();
        }
        pPoseResonator = attachResonatorToPortal(pPoseResonator);
        System.out.println("team apres methode attach : " + pPoseResonator.getPortal().getTeam());
        System.out.println("fin de la methode");
        return (lPreviousId != pPoseResonator.getPortal().getTeam().getId());
    }*/

    public static Boolean isTeamChangedAfterResonatorPoseOnPortal(CPortalEntity pPortal, CTeamEntity pTeam){
        System.out.println("dans la methode");
        if (pTeam != null){
            int lPreviousId = pTeam.getId();
            return (lPreviousId != pPortal.getTeam().getId());
        }
        else{
            if (pPortal.getTeam() != null){
                return true;
            }
            return false;
        }

    }

    /*public static CAttackBuilding applyAttack(CAttackBuilding pAttackBuilding) {
        if (pAttackBuilding.getConsumable().getName().equals("Attack")) {
            pAttackBuilding.getPlayer().attack(pAttackBuilding.getBuilding(), pAttackBuilding.getConsumable());
            //TODO add XP degat * 10
            //pAttackBuilding.getPlayer().addXP();

        } else {
            System.out.println("Consommable non approrié");
        }
        return pAttackBuilding;
    }*/


    public static Boolean isPortalTeamOfBuildingChanged(ABuildingEntity pBuilding){
        //CAttackBuilding lAttackBuilding = applyAttack(pAttackBuilding);
        if (pBuilding instanceof CResonatorEntity){
            if (isDeadBuilding(pBuilding)){
                int lTeamId = pBuilding.getPortal().getTeam().getId();
                pBuilding.getPortal().attributeTeam();
                int lTeamToCompare = pBuilding.getPortal().getTeam().getId();
                return (lTeamId != lTeamToCompare);
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }

    public static Boolean isDeadBuilding(ABuildingEntity pBuilding){
        /*System.out.println("pv building avant atq : " + pAttackBuilding.getBuilding().getEnergy());
        //CAttackBuilding lAttackBuilding = applyAttack(pAttackBuilding);
        System.out.println("pv building apres atq : " + pAttackBuilding.getBuilding().getEnergy());*/
        return pBuilding.getEnergy() <= 0 ;
    }

    public static ABuildingEntity applyAttack(ABuildingEntity pBuilding, CConsumableEntity pConsumable, CPlayerEntity pPlayer) {
        System.out.println("--->" +pConsumable==null);
        System.out.println("((((" +pBuilding==null);
        System.out.println("pppppppppp->" +pPlayer==null);
        if (pConsumable.getName().equals("Attack")) {
            pPlayer.attack(pBuilding, pConsumable);
            //TODO add XP degat * 10
            //pAttackBuilding.getPlayer().addXP();

        } else {
            System.out.println("Consommable non approrié");
        }
        return pBuilding;
    }

    /*public static Boolean isDeadBuilding(CAttackBuilding pAttackBuilding){
        System.out.println("pv building avant atq : " + pAttackBuilding.getBuilding().getEnergy());
        //CAttackBuilding lAttackBuilding = applyAttack(pAttackBuilding);
        System.out.println("pv building apres atq : " + pAttackBuilding.getBuilding().getEnergy());
        if (pAttackBuilding.getBuilding().getEnergy() <= 0){
            if (pAttackBuilding.getBuilding() instanceof CResonatorEntity){
                pAttackBuilding.getBuilding().getPortal().removeResonator((CResonatorEntity)pAttackBuilding.getBuilding());
            }
            else{
                pAttackBuilding.getBuilding().getPortal().removeBuilding(pAttackBuilding.getBuilding());
            }
            return true;
        }
        else{
            return false;
        }
    }*/

    /*public static Boolean isPortalTeamOfBuildingChanged(ABuildingEntity pBuilding, CConsumableEntity pConsumable, CPlayerEntity pPlayer){
        CAttackBuilding lAttackBuilding = applyAttack(pAttackBuilding);
        if (lAttackBuilding.getBuilding() instanceof CResonatorEntity){
            if (isDeadBuilding(lAttackBuilding)){
                int lTeamId = lAttackBuilding.getBuilding().getPortal().getTeam().getId();
                lAttackBuilding.getBuilding().getPortal().attributeTeam();
                int lTeamToCompare = lAttackBuilding.getBuilding().getPortal().getTeam().getId();
                return (lTeamId != lTeamToCompare);
                }
            else{
                return false;
            }
        }
        else{
            return false;
        }
<<<<<<< HEAD
=======

    }*/



}

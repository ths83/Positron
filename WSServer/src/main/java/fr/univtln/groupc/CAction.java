package fr.univtln.groupc;

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

    public static CPoseResonator attachResonatorToPortal(CPoseResonator pPoseResonator){
        System.out.println("salut attach 1");
        if (pPoseResonator.getPortal().getResonators().size() < 8){
            System.out.println("salut attach 2");
            // todo : demander a xavier d expliquer ce if
            if (pPoseResonator.getResonator().getOwner().getLevel() >= pPoseResonator.getResonator().getLevel()){
                System.out.println("salut attach 3");
                pPoseResonator.getPortal().addResonator(pPoseResonator.getResonator());
                System.out.println("nb reso au portail : " + pPoseResonator.getPortal().getResonators().size());
                pPoseResonator.getPortal().attributeTeam();
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
    }

}

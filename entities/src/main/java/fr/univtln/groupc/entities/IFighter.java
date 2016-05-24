package fr.univtln.groupc.entities;

/**
 * Created by marti on 02/05/2016.
 */
public interface IFighter {
    public void attack(ITarget pTarget, CConsumableEntity pAmmunition);
    public CTeamEntity getFighterTeam();

}
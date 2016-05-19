package fr.univtln.groupc.entities;

/**
 * Created by marti on 02/05/2016.
 */
public interface IFighter {
    public ITarget attack(ITarget pTarget, int pDamage);
    public CTeamEntity getTeamOfFighter();
}
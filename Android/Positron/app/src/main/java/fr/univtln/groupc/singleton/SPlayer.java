package fr.univtln.groupc.singleton;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.rest.CRestGet;

/**
 * Created by tperron710 on 31/05/16.
 */
public class SPlayer extends CPlayerEntity{

    /**
     * singleton pour l'utilisateur courant de l'application
     * ------
     * player singleton for current player
     */

    private final CPlayerEntity mPlayer;

    private static SPlayer ourInstance = new SPlayer();

    public static SPlayer getInstance() {
        return ourInstance;
    }

    private SPlayer() {
        mPlayer = new CRestGet().getPlayerByID(1);
    }
}

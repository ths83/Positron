package fr.univtln.groupc.activities.google;

import fr.univtln.groupc.entities.CPlayerEntity;

/**
 * Created by arouani277 on 30/05/16.
 */
public class SCurrentPlayer {

    public static CPlayerEntity mPlayer = null;
    public static String  mMail = null;

    private static SCurrentPlayer ourInstance = new SCurrentPlayer();

    public static SCurrentPlayer getInstance() {
        return ourInstance;
    }

    private SCurrentPlayer() {
    }
}

package fr.univtln.groupc.activities.google;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import fr.univtln.groupc.entities.CPlayerEntity;

/**
 * Created by arouani277 on 30/05/16.
 */
public class CCurrentPlayer {

    public static CPlayerEntity mPlayer = null;
    private static CCurrentPlayer ourInstance = new CCurrentPlayer();

    public static CCurrentPlayer getInstance() {
        return ourInstance;
    }

    private CCurrentPlayer() {
    }
}

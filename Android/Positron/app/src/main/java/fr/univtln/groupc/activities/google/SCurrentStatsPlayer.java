package fr.univtln.groupc.activities.google;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.stats.CStatsPlayer;

/**
 * Created by boblinux on 01/06/16.
 */
public class SCurrentStatsPlayer {

    public static CStatsPlayer mStatsPlayer = null;

    private static SCurrentStatsPlayer ourInstance = new SCurrentStatsPlayer();

    public static SCurrentStatsPlayer getInstance() {
        return ourInstance;
    }

    private SCurrentStatsPlayer() {
    }
}

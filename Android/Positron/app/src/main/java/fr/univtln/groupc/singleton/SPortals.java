package fr.univtln.groupc.singleton;

import java.util.List;

import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.rest.CRestGet;

/**
 * Created by tperron710 on 31/05/16.
 */
public class SPortals extends CPortalEntity{

    /**
     * singleton pour les portails dans l'application
     * ------
     * portals singleton
     */

    private final List<CPortalEntity> mPortals ;

    private static SPortals ourInstance = new SPortals();

    public static SPortals getInstance() {
        return ourInstance;
    }

    private SPortals() {
        mPortals = new CRestGet().getPortalsRest();
    }

}

package fr.univtln.groupc;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.stats.CStatPortalHacked;
import junit.framework.TestCase;

import java.util.Date;
import java.util.List;

/**
 * Created by marti on 09/06/2016.
 */
public class CStatPortalHackedTest extends TestCase {
    CCrudMethods mCrudMethods = new CCrudMethods();


    public void testHackgetAll() throws Exception {
        System.out.println("salut");
        CStatPortalHacked lStat = new CStatPortalHacked.CStatPortalHackedBuilder().date(new Date()).player(99).build();
        mCrudMethods.openTransaction();
        mCrudMethods.create(lStat);
        mCrudMethods.commitTransaction();
        System.out.println("ok");
        List<CStatPortalHacked> lList = (List<CStatPortalHacked>)mCrudMethods.findWithNamedQuery(CStatPortalHacked.GET_BY_PLAYER_ID, CQueryParameter.with("mPlayerId", 99).parameters());
        System.out.println(lList.size());
    }
}

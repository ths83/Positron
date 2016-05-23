package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdurbec066 on 19/05/16.
 */
public class CTestGN {
    public static void main(String[] args) {
        CCrudMethods lCrud = new CCrudMethods();

        CTeamEntity lTeam = new CTeamEntity.CTeamBuilder(1).build();


        CKeyEntity lKey1 = new CKeyEntity.CKeyBuilder(400).build();
        List<CKeyEntity> lKey1s = new ArrayList<>();
        lKey1s.add(lKey1);
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(200).latitude(43.136016).longitude(6.018558).keys(lKey1s).team(lTeam).build();

        List<CKeyEntity> lKey2s = new ArrayList<>();
        CKeyEntity lKey2 = new CKeyEntity.CKeyBuilder(401).build();
        lKey2s.add(lKey2);
        CPortalEntity lP2 = new CPortalEntity.CPortalBuilder(201).latitude(43.135362).longitude(6.019414).keys(lKey2s).team(lTeam).build();

        List<CKeyEntity> lKey3s = new ArrayList<>();
        CKeyEntity lKey3 = new CKeyEntity.CKeyBuilder(402).build();
        lKey3s.add(lKey3);
        CPortalEntity lP3 = new CPortalEntity.CPortalBuilder(202).latitude(43.135208).longitude(6.018227).keys(lKey3s).team(lTeam).build();


        CPlayerEntity lPlayer = lCrud.find(CPlayerEntity.class, 1);
        lPlayer.addObjects(lKey1);
        lPlayer.addObjects(lKey2);
        lPlayer.addObjects(lKey3);

        lCrud.update(lPlayer);

    }
}

package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;
import fr.univtln.groupc.stats.CStatsPlayer;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestStatsPlayerTest extends TestCase{
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;


    public void testPostStatsPlayerService() throws Exception {

        CTurretEntity lTurret = new CTurretEntity
                .CTurretBuilder(78678687).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200)
                .name("t1").radius(100).build();
        ArrayList <ABuildingEntity> lTurrets= new ArrayList<>();
        lTurrets.add(lTurret);

        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(78687678).energy(100)
                .latitude(10.5).energyMax(200)
                .level(9).longitude(5.2).name("cr")
                .radius(54).build();

        ArrayList <CResonatorEntity> lResonators = new ArrayList<>();
        lResonators.add(lResonator);

        CConsumableEntity lConsumableEntity = new CConsumableEntity.CConsumableBuilder(1).name("bob").rarity(5).build();
        ArrayList <CConsumableEntity> lConsumables = new ArrayList<>();
        lConsumables.add(lConsumableEntity);

        CPlayerEntity lPlayerEntity1 = new CPlayerEntity.CPlayerBuilder(1).email("zad").energy(5).build();

        CStatsPlayer lStatsPlayer = new CStatsPlayer.CStatsPlayerBuilder(1).player(lPlayerEntity1).build();
        // TODO : getters : resonatorsDestroyed() and resonatorsBuilt() doesn't work

        mJson = mMapper.writeValueAsString(lStatsPlayer);
        lResponse = mWebResource.path("/statsPlayer").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetStatsPlayerService() throws Exception {
        CStatsPlayer lStatsPlayer = mMapper.readValue(mWebResource.path("/statsPlayer/1").get(String.class), CStatsPlayer.class);
        assertEquals(lStatsPlayer.getmID(), 1);
    }

    public void testDeleteStatsPlayerService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/statsPlayer/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }

}

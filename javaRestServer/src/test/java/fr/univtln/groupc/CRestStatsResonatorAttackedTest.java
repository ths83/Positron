package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestStatsResonatorAttackedTest extends TestCase {
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

/*
    public void testPostStatsResonatorAttackedService() throws Exception {
        CPlayerEntity lPlayerEntity1 = new CPlayerEntity.CPlayerBuilder(1).email("zad").energy(5).build();
        CPlayerEntity lPlayerEntity2 = new CPlayerEntity.CPlayerBuilder(2).email("zad").energy(5).build();
        CResonatorEntity lResonatorEntity = new CResonatorEntity.CResonatorBuilder(1).energy(5).level(10).build();

        CStatsResonatorAttacked lStatsResonatorAttacked = new CStatsResonatorAttacked.CStatsResonatorAttackedBuilder(1).resonator(lResonatorEntity).cpt(5)
                .attacker(lPlayerEntity1).owner(lPlayerEntity2).build();
        mJson = mMapper.writeValueAsString(lStatsResonatorAttacked);
        lResponse = mWebResource.path("/statsResonatorAttacked").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetStatsResonatorAttackedService() throws Exception {
        CStatsResonatorAttacked lStatsResonatorAttacked = mMapper.readValue(mWebResource.path("/statsResonatorAttacked/1").get(String.class), CStatsResonatorAttacked.class);
        assertEquals(lStatsResonatorAttacked.getmId(), 1);
    }

    public void testDeleteStatsResonatorAttackedService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/statsResonatorAttacked/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
    */
}

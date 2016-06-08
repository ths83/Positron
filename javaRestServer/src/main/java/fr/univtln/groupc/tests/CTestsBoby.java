package fr.univtln.groupc.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;

import java.io.IOException;

/**
 * Created by arouani277 on 02/05/16.
 */
public class CTestsBoby {
    public static void main(String[] args) throws IOException {
/*
        CTurretEntity c1 = new CTurretEntity
                .CTurretBuilder(78678687).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).latitude(10.5)
                .longitude(11.2).name("c1").radius(100).build();

        CResonatorEntity cr = new CResonatorEntity.CResonatorBuilder(78687678).energy(100)
                .latitude(10.5).energyMax(200)
                .level(9).longitude(5.2).name("cr")
                .radius(54).build();

        List<AObjectEntity> objects = new ArrayList();
        objects.add(c1);
        objects.add(cr);

        List<CResonatorEntity> resonators = new ArrayList<CResonatorEntity>();
        resonators.add(cr);

        CPortalEntity cpr = new CPortalEntity.CPortalBuilder(2).latitude(10).longitude(5.2).objects(objects).resonators(resonators).build();
        System.out.println(cpr.toString());

        List<CPortalEntity> portals = new ArrayList<>();
        System.out.println(portals.add(cpr));

        CTeamEntity ctm = new CTeamEntity.CTeamBuilder(78678).color("red").portals(portals).build();
        System.out.println(ctm.toString());


        CPlayerEntity cp = new CPlayerEntity.CPlayerBuilder(5858).email("bob@z.fr").nickname("mahmoud").team(ctm).build();
        System.out.println(cp.toString());


        CSkillEntity csk = new CSkillEntity.CSkillBuilder(5).cost(140).level(10).name("ntm").build();
        System.out.println(csk.toString());
*/
        Client c = Client.create();
        WebResource mWebResource = c.resource(CServer.BASE_URI);
        ObjectMapper mMapper = new ObjectMapper();

        CTeamEntity lTeamToPost = new CTeamEntity.CTeamBuilder(150).color("vert").build();
        String lJsonTeam = mMapper.writeValueAsString(lTeamToPost);
        ClientResponse lResponse = mWebResource.path("/teams").type("application/json").accept("application/json").post(ClientResponse.class, lJsonTeam);

        CTeamEntity cTeamEntity = mMapper.readValue(mWebResource.path("teams/150").get(String.class), CTeamEntity.class);
        System.out.println(cTeamEntity.toString());


        CTurretEntity lTurretPost = new CTurretEntity
                .CTurretBuilder().level(10).build();
        String lJsonTurret = mMapper.writeValueAsString(lTurretPost);

        lResponse = mWebResource.path("/turrets").type("application/json").accept("application/json").post(ClientResponse.class, lJsonTurret);
        System.out.println(lResponse.getStatus());


    }
}
package fr.nmartinez016;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

/**
 * Created by marti on 04/05/2016.
 */

@Path("classa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CServiceA {

    CCrudServiceBean<CClassA> lCrud = new CCrudServiceBean<CClassA>();

    @POST
    @Path("/salut")
    public Response create(String lA){
        System.out.println("salut");

        ObjectMapper lMapper = new ObjectMapper();

        CClassA pA = null;
        System.out.println("ok ?");
        try {
            pA = lMapper.readValue(lA, CClassA.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
        lCrud.create(pA);
        return Response.status(201).entity(lA).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(){
        System.out.println("salut!");
        CClassA lA = new CClassA(5, "jaja");
        CClassB lB = new CClassB(6, "jajab");
        lA.addB(lB);
        lB.setmClassA(lA);
        ObjectMapper lMapper = new ObjectMapper();
        String retour = null;
        try {
            retour = lMapper.writeValueAsString(lA);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retour;
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int pId){
        lCrud.delete(CClassA.class, pId);
        return Response.status(200).build();
    }

}

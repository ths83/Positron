package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CShieldEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by xdurbec066 on 18/05/16.
 */

@Path("/shields")
public class CShieldService {


        private CCrudMethods mCrudMethods = new CCrudMethods();
        private ObjectMapper mMapper = new ObjectMapper();

        @POST
        @Consumes("application/json")
        public Response createShield(String pShieldJson){
            CShieldEntity lShield = null;
            try {
                lShield = mMapper.readValue(pShieldJson, CShieldEntity.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCrudMethods.create(lShield);
            return Response.status(201).entity(pShieldJson).build();

        }

        /**
         * @param pId
         * @return CShieldEntity
         */
        @GET
        @Produces("application/json")
        @Path("/{id}")
        public String readShield(@PathParam("id") int pId) {
            //return (CShieldEntity) mCrudMethods.find(CShieldEntity.class, pId);
            String lJsonValue = null;
            CShieldEntity lShield = mCrudMethods.find(CShieldEntity.class, pId);
            try {
                lJsonValue = mMapper.writeValueAsString(lShield);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lJsonValue;
        }

        /**
         * @return List<CShieldEntity>
         */
        @GET
        @Produces("application/json")
        @Path("/all")
        public String readAll(){
            //return mCrudMethods.findWithNamedQuery(CShieldEntity.GET_ALL);
            String lJsonValue = null;
            List<CShieldEntity> lShields = mCrudMethods.findWithNamedQuery(CShieldEntity.GET_ALL);
            try {
                lJsonValue = mMapper.writeValueAsString(lShields);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lJsonValue;
        }

        @PUT
        @Consumes("application/json")
        @Produces("application/json")
        public Response update(String pShieldJson){
            CShieldEntity lShield = null;
            try {
                lShield = mMapper.readValue(pShieldJson, CShieldEntity.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCrudMethods.update(lShield);
            return Response.status(200).build();
        }


        @DELETE
        @Consumes("application/json")
        @Path("/{id}")
        public Response delete(@PathParam("id") int pId){
            mCrudMethods.delete(CShieldEntity.class, pId);
            return Response.status(200).build();
        }



}

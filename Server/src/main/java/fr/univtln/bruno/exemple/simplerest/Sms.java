package fr.univtln.bruno.exemple.simplerest;

import javax.ws.rs.*;
import java.util.List;

// The Java class will be hosted at the URI path "/myresource"
@Path("/bank")
//@Produces({"application/json", "application/xml"})
@Produces("application/json")

public class Sms {

    private final static ModelSms modeleSms = new ModelSms();

    public static final CSmsReceived[] smsInit = {new CSmsReceived.SmsReceivedBuilder()
            .Data("coords X Y").DateReceiving("janvier").Phone("+33565456").ID(0).build(),
            new CSmsReceived.SmsReceivedBuilder().Data("coords X Y").ID(1).build()};

    static {
        modeleSms.ajouterSms(smsInit[0]);
        modeleSms.ajouterSms(smsInit[1]);
    }

    @PUT
    @Path("sms")
    public void ajouterSms(@QueryParam("data") String data, @QueryParam("id") int id) {
        modeleSms.ajouterSms(data, id);
    }

    @DELETE
    @Path("sms/{id}")
    public void supprimerSms(@PathParam("id") final int ID) {
        modeleSms.supprimerSms(ID);
    }

    @GET
    @Path("sms/{id}")
    public CSmsReceived getSms(@PathParam("id") final int ID) {
        CSmsReceived smsReceived = modeleSms.getSMS(ID);
        return smsReceived;
    }

    @GET
    @Path("allsms")
    public List<CSmsReceived> getSms() {
        return modeleSms.getAuteurs();
    }
}

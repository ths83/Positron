
package fr.univtln.bruno.exemple.simplerest;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import junit.framework.TestCase;


public class MainTest extends TestCase {
/*
    private HttpServer httpServer;
    
    private WebResource r;

    public MainTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        //start the Grizzly2 web container 
        httpServer = Main.startServer();

        // create the client
        Client c = Client.create();
        r = c.resource(Main.BASE_URI);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        httpServer.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    /*
    public void testMyResource() {
        String responseAuteurAsJson = r.path("biblio/auteur/1").get(String.class);
        assertEquals("{\"ID\":\"1\",\"nom\":\"Durand\",\"prenom\":\"Marie\"}", responseAuteurAsJson);

        Auteur responseAuteur = r.path("biblio/auteur/1").get(Auteur.class);
        assertEquals(Sms.smsInit[1], responseAuteur);

    }

    /**
     * Test if a WADL document is available at the relative path
     * "application.wadl".
     */
    /*
    public void testApplicationWadl() {
        String serviceWadl = r.path("application.wadl").
                accept(MediaTypes.WADL).get(String.class);
                
        assertTrue(serviceWadl.length() > 0);
    }
    */
}

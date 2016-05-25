package fr.univtln.groupc.server;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.testing.http.json.MockJsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.*;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * Created by arouani277 on 25/05/16.
 */
public class CServerGoogle {

    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new MockJsonFactory();

    private static final int CLIENT_ID = 0;

    public static void main(String[] args) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                //.setAudience(Arrays.asList(CLIENT_ID))
                        // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                        // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                        // "accounts.google.com". If you need to verify tokens from multiple sources, build
                        // a GoogleIdTokenVerifier for each issuer and try them both.
                //.("https://accounts.google.com")
                .build();


        // TODO (Receive idTokenString by HTTPS POST)
        String idTokenString = "recieved by HTTPS POST";

        // We don't make verification for now
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getUserId();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
        }


    }
}

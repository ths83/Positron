package fr.univtln.groupc.server;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

/**
 * Created by arouani277 on 25/05/16.
 */
public class CServerGoogle2 {
    /*
    private final List mClientIDs;
    private final String mAudience;
    private final GoogleIdTokenVerifier mVerifier;
    private final JsonFactory mJFactory;
    private String mProblem = "Verification failed. (Time-out?)";

    public static void main(String[] args) {
        // Set up the HTTP transport and JSON factory
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

// Set up OAuth 2.0 access of protected resources
// using the refresh and access tokens, automatically
// refreshing the access token when it expires
        GoogleAccessProtectedResource requestInitializer =
                new GoogleAccessProtectedResource(accessToken, httpTransport,
                        jsonFactory, clientId, clientSecret, refreshToken);

// set up global Oauth2 instance
        Oauth2 oauth2 = new Oauth2.Builder(httpTransport, jsonFactory, requestInitializer)
                .setApplicationName("Google-OAuth2Sample/1.0").build();

        Userinfo userinfo = oauth2.userinfo().get().execute();
    }
    */
}

package fr.univtln.groupc.signin.google;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;

import fr.univtln.groupc.activities.google.SCurrentPlayer;
import fr.univtln.groupc.activities.map.CMapsActivity;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CSignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String serverClientId = "38429459185-kjm6ss6jbb1msmpq5i8sggt4r5428lcn.apps.googleusercontent.com";
    private static final String TAG = "CSignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private static final String AUTHENTIFICATION_SUCCESS_FRENCH = "Connexion établie !";

    private Intent mMapIntent;
    private Button mMapButtonLauncher;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;

    Intent getmMapIntent = new Intent(this, CMapsActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapButtonLauncher = (Button) findViewById(R.id.sign_in_button);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestEmail()
                .requestIdToken(serverClientId)
                .requestServerAuthCode(serverClientId, false)
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }


    /**
     * method applied to connexion button
     * @param view
     */
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.sign_in_button:
                Log.i(TAG,"clicked");
                signIn();
                break;
        }
    }

    /**
     * ask connexion to Google API
     */
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> lOptPenRes = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (lOptPenRes.isDone()) {
            Toast.makeText(getApplicationContext(), "Authentification succeded !",Toast.LENGTH_LONG).show();
            GoogleSignInResult lResult = lOptPenRes.get();
            handleSignInResult(lResult);
        } else {
            lOptPenRes.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult pConnectionResult) {
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            SCurrentPlayer.mMail = acct.getEmail();
            Toast.makeText(getBaseContext(),"bienvenue : " + acct.getDisplayName(),Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(),"server auth code : " + acct.getServerAuthCode(),Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(),"granted scopres : " + acct.getGrantedScopes(),Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(),"id token : " + acct.getIdToken(),Toast.LENGTH_SHORT).show();
            startActivity(getmMapIntent);
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(getBaseContext(),"degage",Toast.LENGTH_SHORT).show();
            //startActivity(getmMapIntent);

        }
    }

}

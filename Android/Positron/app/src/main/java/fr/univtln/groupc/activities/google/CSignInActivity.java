package fr.univtln.groupc.activities.google;

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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import fr.univtln.groupc.activities.map.CMapsActivity;
import fr.univtln.groupc.rest.CRestPlayer;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CSignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "CSignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private static final String AUTHENTIFICATION_SUCCESS_FRENCH = "Connexion établie !";

    private Intent mMapIntent;
    private Button mMapButtonLauncher;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapButtonLauncher = (Button) findViewById(R.id.sign_in_button);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions lGso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, lGso)
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
        Intent lSignInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(lSignInIntent, RC_SIGN_IN);
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
    public void onActivityResult(int pRequestCode, int pResultCode, Intent pData) {
        super.onActivityResult(pRequestCode, pResultCode, pData);
        if (pRequestCode == RC_SIGN_IN) {
            GoogleSignInResult lResult = Auth.GoogleSignInApi.getSignInResultFromIntent(pData);
            handleSignInResult(lResult);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult pConnectionResult) {
    }

    private void handleSignInResult(GoogleSignInResult pResult) {
        Log.d(TAG, "handleSignInResult:" + pResult.isSuccess());
        if (pResult.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount lAcct = pResult.getSignInAccount();

            // Mettre le compte courant disponible pour tout le programme globalement
            if (lAcct != null) {
                SCurrentPlayer.mPlayer = new CRestPlayer().getPlayerByMail(lAcct.getEmail());
            }

            String idToken = null;
            if (lAcct != null) {
                idToken = lAcct.getIdToken();
            }

            // Show signed-in UI.
            Log.d(TAG, "idToken:" + idToken);
            mMapIntent = new Intent(this,CMapsActivity.class);
            // on lance la map
            startActivity(mMapIntent);
            Toast.makeText(getBaseContext(),AUTHENTIFICATION_SUCCESS_FRENCH,Toast.LENGTH_SHORT).show();
            /*mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            updateUI(true);*/
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

}

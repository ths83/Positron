package fr.univtln.groupc.activities.portals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.groupc.activities.map.CMapsActivity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.rest.CRestGet;
import fr.univtln.groupc.rest.CRestPost;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CPutPortalsView extends AppCompatActivity {

    private List<CKeyEntity> mKeys = new ArrayList<>();
    private ListView mListView;
    private CKeyEntity mKey;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cput_portals_view);
        mListView = (ListView) findViewById(R.id.listview);
        initKeys();
        Log.d("test", "nb de clef: " + mKeys.size());
        for (CKeyEntity lKey : mKeys){
            Log.d("test", "salut la clef :\n" + lKey);
        }

        CKeyAdapter lAdapter = new CKeyAdapter(CPutPortalsView.this, mKeys);
        mListView.setAdapter(lAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mKey = mKeys.get(position);
                Toast.makeText(getBaseContext(), "clef de portail : " + Integer.toString(mKey.getPortal().getId()), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cput_portals_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void initKeys(){
        List<CKeyEntity> lKeys = new ArrayList<>();
        CRestGet lRest = new CRestGet();
        CPlayerEntity lPlayer = lRest.getPlayerByID(1);
        mKeys = lPlayer.getKeys();
        Log.d("test", "liste clefs ->\n" + mKeys);

    }

    public void validateKey(View view){
        CRestGet lRest = new CRestGet();
        CPortalEntity lPortal1 = lRest.getPortalByIdRest(200);
        CPortalEntity lPortal2 = mKey.getPortal();
        List<CPortalEntity> lPortals = new ArrayList<>();
        lPortals.add(lPortal1);
        lPortals.add(lPortal2);
        CLinkEntity lLinkToCreate = new CLinkEntity.CLinkBuilder(756).portals(lPortals).build();
        CRestPost lPost = new CRestPost();
        lPost.postLinkRest(lLinkToCreate);
        mIntent = new Intent(this, CMapsActivity.class);
        startActivity(mIntent);

    }
}

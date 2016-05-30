package fr.univtln.groupc.activities.profil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CSkillEntity;
import fr.univtln.groupc.rest.CRestGet;
import fr.univtln.groupc.rest.CRestUpdate;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CSkillTree extends AppCompatActivity {

    TextView mFree_SkillPoints = (TextView) findViewById(R.id.text_View_free_skill_point);

    CPlayerEntity mPlayer = new CPlayerEntity.CPlayerBuilder(1).xp(5000).build();
   // Map<Button, CSkillEntity> mSkillByButton = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cskill_tree);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cskill_tree, menu);
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

    public int calculateFreeSkillPoint(CPlayerEntity pPlayer){
        int lSkillPoints = pPlayer.getLevel()*2;
        int lSkillPointOwned = 0, lFreePoint = 0;

        for(CSkillEntity lSkill : pPlayer.getSkills()){
            lSkillPointOwned = lSkillPoints + lSkill.getLevel();
        }
        lFreePoint = lSkillPoints - lSkillPointOwned;

        mFree_SkillPoints.setText(lFreePoint);

        return lFreePoint;
    }

    public void resetSkills(View view){
        mPlayer.setSkills(null);
        calculateFreeSkillPoint(mPlayer);
    }

    public void selectSkill_Maintenance_1(View pView){
       CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
       CSkillEntity lSkillWanted =lGet.getSkillByID(11);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }

    }
    public void selectSkill_Maintenance_21(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(121);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }

    }
    public void selectSkill_Maintenance_22(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(122);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }

    }
    public void selectSkill_Maintenance_3(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(13);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }


    public void selectSkill_Attack_1(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(21);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }
    public void selectSkill_Attack_21(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(221);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }
    public void selectSkill_Attack_22(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(222);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }

    }
    public void selectSkill_Attack_3(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(23);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }

    public void selectSkill_Hacking_1(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(31);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }
    public void selectSkill_Hacking_21(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(321);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }
    public void selectSkill_Hacking_22(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(322);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }

    }
    public void selectSkill_Hacking_3(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(33);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }

    public void selectSkill_Scout_1(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(41);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }
    public void selectSkill_Scout_21(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(421);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }
    public void selectSkill_Scout_22(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(422);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }
    public void selectSkill_Scout_3(View pView){

        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(43);

        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            lUpDate.updatePlayerRest(mPlayer);
        }
    }


}

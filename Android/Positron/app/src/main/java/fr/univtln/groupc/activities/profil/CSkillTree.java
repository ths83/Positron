package fr.univtln.groupc.activities.profil;

import android.graphics.Color;
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


    Button mButtonReset = (Button) findViewById(R.id.button_Reset_Skill);

    Button mButtonMaintenance_1;
    Button mButtonMaintenance_21;
    Button mButtonMaintenance_22;
    Button mButtonMaintenance_3;

    Button mButtonAttack_1;
    Button mButtonAttack_21;
    Button mButtonAttack_22;
    Button mButtonAttack_3;

    Button mButtonHacker_1;
    Button mButtonHacker_21;
    Button mButtonHacker_22;
    Button mButtonHacker_3;

    Button mButtonScout_1;
    Button mButtonScout_21;
    Button mButtonScout_22;
    Button mButtonScout_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cskill_tree);

        mButtonReset = (Button) findViewById(R.id.button_Reset_Skill);

        mButtonMaintenance_1 = (Button) findViewById(R.id.Maintenance_1);
        mButtonMaintenance_21 = (Button) findViewById(R.id.Maintenance_21);
        mButtonMaintenance_22 = (Button) findViewById(R.id.Maintenance_22);
        mButtonMaintenance_3 = (Button) findViewById(R.id.Maintenance_3);

        mButtonAttack_1 = (Button) findViewById(R.id.Attack_1);
        mButtonAttack_21 = (Button) findViewById(R.id.Attack_21);
        mButtonAttack_22 = (Button) findViewById(R.id.Attack_22);
        mButtonAttack_3 = (Button) findViewById(R.id.Attack_3);

        mButtonHacker_1 = (Button) findViewById(R.id.Hacking_1);
        mButtonHacker_21 = (Button) findViewById(R.id.Hacking_21);
        mButtonHacker_22 = (Button) findViewById(R.id.Hacking_22);
        mButtonHacker_3 = (Button) findViewById(R.id.Hacking_3);

        mButtonScout_1 = (Button) findViewById(R.id.Scout_1);
        mButtonScout_21 = (Button) findViewById(R.id.Scout_21);
        mButtonScout_22 = (Button) findViewById(R.id.Scout_22);
        mButtonScout_3 = (Button) findViewById(R.id.Scout_3);

        enableDisableSkillTree(mPlayer);
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


    public void enableDisableSkillTree(CPlayerEntity pPlayer){

        int lLevelAttack = pPlayer.getLevelOnSkillBranch("Attack");
        int lLevelMaintenance = pPlayer.getLevelOnSkillBranch("Maintenance");
        int lLevelHack = pPlayer.getLevelOnSkillBranch("Hack");
        int lLevelScout = pPlayer.getLevelOnSkillBranch("Scout");

        mButtonMaintenance_1.setEnabled(false);
        mButtonMaintenance_1.setBackgroundColor(Color.GRAY);
        mButtonMaintenance_21.setEnabled(false);
        mButtonScout_21.setBackgroundColor(Color.GRAY);
        mButtonMaintenance_22.setEnabled(false);
        mButtonMaintenance_22.setBackgroundColor(Color.GRAY);
        mButtonMaintenance_3.setEnabled(false);
        mButtonMaintenance_3.setBackgroundColor(Color.GRAY);
        mButtonAttack_1.setEnabled(false);
        mButtonAttack_1.setBackgroundColor(Color.GRAY);
        mButtonAttack_21.setEnabled(false);
        mButtonAttack_21.setBackgroundColor(Color.GRAY);
        mButtonAttack_22.setEnabled(false);
        mButtonAttack_22.setBackgroundColor(Color.GRAY);
        mButtonAttack_3.setEnabled(false);
        mButtonAttack_3.setBackgroundColor(Color.GRAY);
        mButtonHacker_1.setEnabled(false);
        mButtonHacker_1.setBackgroundColor(Color.GRAY);
        mButtonHacker_21.setEnabled(false);
        mButtonHacker_21.setBackgroundColor(Color.GRAY);
        mButtonHacker_22.setEnabled(false);
        mButtonHacker_22.setBackgroundColor(Color.GRAY);
        mButtonHacker_3.setEnabled(false);
        mButtonHacker_3.setBackgroundColor(Color.GRAY);
        mButtonScout_1.setEnabled(false);
        mButtonScout_1.setBackgroundColor(Color.GRAY);
        mButtonScout_21.setEnabled(false);
        mButtonScout_21.setBackgroundColor(Color.GRAY);
        mButtonScout_22.setEnabled(false);
        mButtonScout_22.setBackgroundColor(Color.GRAY);
        mButtonScout_3.setEnabled(false);
        mButtonScout_3.setBackgroundColor(Color.GRAY);


        if(lLevelMaintenance < 3){
            mButtonMaintenance_3.setEnabled(true);
            mButtonMaintenance_3.setBackgroundColor(Color.CYAN);

            if(lLevelMaintenance <2){
                mButtonMaintenance_21.setEnabled(true);
                mButtonMaintenance_21.setBackgroundColor(Color.CYAN);
                mButtonMaintenance_22.setEnabled(true);
                mButtonMaintenance_22.setBackgroundColor(Color.CYAN);

                if(lLevelMaintenance < 1){
                    mButtonMaintenance_1.setEnabled(true);
                    mButtonMaintenance_1.setBackgroundColor(Color.CYAN);
                }
            }
        }


        if(lLevelAttack < 3){
            mButtonAttack_3.setEnabled(true);
            mButtonAttack_3.setBackgroundColor(Color.CYAN);

            if(lLevelAttack <2){
                mButtonAttack_22.setEnabled(true);
                mButtonAttack_22.setBackgroundColor(Color.CYAN);
                mButtonAttack_21.setEnabled(true);
                mButtonAttack_21.setBackgroundColor(Color.CYAN);

                if(lLevelAttack < 1){
                    mButtonAttack_1.setEnabled(true);
                    mButtonAttack_1.setBackgroundColor(Color.CYAN);
                }
            }
        }


        if(lLevelHack < 3){
            mButtonHacker_3.setEnabled(true);
            mButtonHacker_3.setBackgroundColor(Color.CYAN);

            if(lLevelHack <2){
                mButtonHacker_21.setEnabled(true);
                mButtonHacker_21.setBackgroundColor(Color.CYAN);
                mButtonHacker_22.setEnabled(true);
                mButtonHacker_22.setBackgroundColor(Color.CYAN);

                if(lLevelHack < 1){
                    mButtonHacker_1.setEnabled(true);
                    mButtonHacker_1.setBackgroundColor(Color.CYAN);
                }
            }
        }

        if(lLevelScout < 3){
            mButtonScout_3.setEnabled(true);
            mButtonScout_3.setBackgroundColor(Color.CYAN);

            if(lLevelScout <2){
                mButtonScout_21.setEnabled(true);
                mButtonScout_21.setBackgroundColor(Color.CYAN);
                mButtonScout_22.setEnabled(true);
                mButtonScout_22.setBackgroundColor(Color.CYAN);

                if(lLevelScout < 1){
                    mButtonScout_1.setEnabled(true);
                    mButtonScout_1.setBackgroundColor(Color.CYAN);
                }
            }
        }

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
        enableDisableSkillTree(mPlayer);
    }


    public void selectSkill_Maintenance_1(View pView){
       CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
       CSkillEntity lSkillWanted =lGet.getSkillByID(11);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }


    public void selectSkill_Maintenance_21(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(121);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }


    public void selectSkill_Maintenance_22(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(122);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }


    public void selectSkill_Maintenance_3(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(13);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Attack_1(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(21);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }
    public void selectSkill_Attack_21(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(221);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }
    public void selectSkill_Attack_22(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(222);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Attack_3(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(23);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Hacking_1(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(31);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Hacking_21(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(321);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Hacking_22(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(322);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }

    }
    public void selectSkill_Hacking_3(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(33);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Scout_1(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(41);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Scout_21(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(421);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Scout_22(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(422);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }

    public void selectSkill_Scout_3(View pView){
        CRestGet lGet = new CRestGet();
        CRestUpdate lUpDate = new CRestUpdate();
        CSkillEntity lSkillWanted =lGet.getSkillByID(43);
        if(mPlayer.skillAvailable(lSkillWanted,calculateFreeSkillPoint(mPlayer))){
            mPlayer.addSkill(lSkillWanted);
            calculateFreeSkillPoint(mPlayer);
            lUpDate.updatePlayerRest(mPlayer);
            enableDisableSkillTree(mPlayer);
        }
    }


}

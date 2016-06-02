package nmartinez016.univtln.fr.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.groupc.CPayloadBean;
import fr.univtln.groupc.CPoseResonator;
import fr.univtln.groupc.EPayloadType;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CTeamEntity;

public class MainActivity extends AppCompatActivity {

    //private ClientTyrus mClientTyrus;
    private BroadcastReceiver mBroadCastReceiverWS;
    private List<CPortalEntity> mPortals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent lIntent = new Intent(MainActivity.this, WebSocketService.class);
        startService(lIntent);
        //mClientTyrus = new ClientTyrus();
        //mClientTyrus.start();
        Log.d("tag", "run fait !!");

        mBroadCastReceiverWS = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent pIntent) {
                if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.POSE_RESONATOR.toString())){
                    CPortalEntity lPortal = (CPortalEntity) pIntent.getSerializableExtra(EPayloadType.RESONATOR_POSED.toString());
                    Log.d("tag", "portal recupere : " + lPortal);
                }
            }
        };
    }

    public void clickPose(View view){
        CTeamEntity lTeam = new CTeamEntity.CTeamBuilder(1).color("bleu").build();
        CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(1).nickname("raul").team(lTeam).xp(8000).build();
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(1).build();
        System.out.println("team du portal de base : " + lPortal.getTeam());
        Log.d("tag","nb reso portail base : " + lPortal.getResonators().size());
        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(2).name("reso1").level(5).owner(lPlayer).build();
        CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().playerId(1).portal(lPortal).resonator(lResonator).build();
        CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder()
                .type(EPayloadType.POSE_RESONATOR.toString())
                .objectPoseResonator(lPose).build();
        ClientTyrus.sendMessage(lBean);
    }
}

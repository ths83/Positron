package fr.univtln.groupc.wsclient;

import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.MessageHandler;

import fr.univtln.groupc.CPayloadBean;
import fr.univtln.groupc.EPayloadType;

/**
 * Created by mpesnel786 on 02/06/16.
 */


public class CMessageHandler implements MessageHandler.Whole<CPayloadBean> {

    private CWebSocketService mWebSocketService;
    public static String TYPE = "type";
    public static String PORTAL = "portal";
    public static String PLAYER = "player";
    public static String LINK = "link";
    public static String BUILDING = "building";

    public static String INTENT_TYPE = "intent_type";


    public CMessageHandler(CWebSocketService pService){
        mWebSocketService = pService;
    }

    @Override
    public void onMessage(CPayloadBean pBean) {
        // todo : differents cas

        Log.d("tag", "dans le onMessage : type recu =  " + pBean.getType());


        if (pBean.getType().equals(EPayloadType.CONNECTED.toString())){
            Log.d("tag", "ok connected recu!");
        }
        else if (pBean.getType().equals(EPayloadType.RESONATOR_POSED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.RESONATOR_POSED.toString());
            lIntent.putExtra(PORTAL, pBean.getResonatorPosed().getPortal());
            lIntent.putExtra(PLAYER, pBean.getResonatorPosed().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        else if (pBean.getType().equals(EPayloadType.PORTAL_CHANGING_TEAM.toString())){
            Log.d("tag", "taille des reso du portal recu : " + pBean.getTeamPortalChanged().getPortal().getResonators().size());

            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.PORTAL_CHANGING_TEAM.toString());
            lIntent.putExtra(PORTAL, pBean.getTeamPortalChanged().getPortal());
            lIntent.putExtra(PLAYER, pBean.getTeamPortalChanged().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        else if (pBean.getType().equals(EPayloadType.LINK_CREATED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.LINK_CREATED.toString());
            lIntent.putExtra(LINK, pBean.getLinkCreated().getLink());
            mWebSocketService.sendBroadcast(lIntent);
        }


        else if (pBean.getType().equals(EPayloadType.BUILDING_ATTACKED.toString())){
            Intent lIntent = new Intent("test");
            lIntent.putExtra(TYPE, EPayloadType.BUILDING_ATTACKED.toString());
            lIntent.putExtra(BUILDING, pBean.getBuildingAttacked().getBuilding());
            lIntent.putExtra(PLAYER, pBean.getBuildingAttacked().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        else if (pBean.getType().equals(EPayloadType.VIRUS_POSED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.VIRUS_POSED.toString());
            lIntent.putExtra(PORTAL, pBean.getVirusPosed().getPortal());
            lIntent.putExtra(PLAYER, pBean.getVirusPosed().getPlayer());
        }

    }
}

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
    public static String HACK_LIMITATION = "hack_limitation";
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
        if (pBean.getType().equals(EPayloadType.RESONATOR_POSED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.RESONATOR_POSED.toString());
            lIntent.putExtra(PORTAL, pBean.getResonatorPosed().getPortal());
            lIntent.putExtra(PLAYER, pBean.getResonatorPosed().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        if (pBean.getType().equals(EPayloadType.PORTAL_CHANGING_TEAM.toString())){
            Log.d("tag", "taille des reso du portal recu : " + pBean.getTeamPortalChanged().getPortal().getResonators().size());

            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.PORTAL_CHANGING_TEAM.toString());
            lIntent.putExtra(PORTAL, pBean.getTeamPortalChanged().getPortal());
            lIntent.putExtra(PLAYER, pBean.getTeamPortalChanged().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        if (pBean.getType().equals(EPayloadType.LINK_CREATED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.LINK_CREATED.toString());
            lIntent.putExtra(LINK, pBean.getLinkCreated().getLink());
            lIntent.putExtra(PLAYER, pBean.getLinkCreated().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }


        if (pBean.getType().equals(EPayloadType.BUILDING_ATTACKED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.BUILDING_ATTACKED.toString());
            lIntent.putExtra(PORTAL, pBean.getBuildingAttacked().getPortal());
            lIntent.putExtra(PLAYER, pBean.getBuildingAttacked().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        if (pBean.getType().equals(EPayloadType.VIRUS_POSED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.VIRUS_POSED.toString());
            //lIntent.putExtra(PORTAL, pBean.getVirusPosed().getPortal());
            lIntent.putExtra(PLAYER, pBean.getVirusPosed().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        if (pBean.getType().equals(EPayloadType.FIELD_CREATED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.FIELD_CREATED.toString());
            lIntent.putExtra(LINK, pBean.getFieldCreated().getLink());
            lIntent.putExtra(PLAYER, pBean.getFieldCreated().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        if (pBean.getType().equals(EPayloadType.PORTAL_HACKED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.PORTAL_HACKED.toString());
            lIntent.putExtra(PLAYER, pBean.getPortalHacked().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }


        if(pBean.getType().equals(EPayloadType.PORTAL_KEY_HACKED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.PORTAL_KEY_HACKED.toString());
            lIntent.putExtra(PLAYER, pBean.getPortalKeyHacked().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        if (pBean.getType().equals(EPayloadType.HACK_LIMITATION.toString())) {
            Log.d("test150", "hack limit -> " + pBean.getHackLimitation());
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.HACK_LIMITATION.toString());
            lIntent.putExtra(HACK_LIMITATION, pBean.getHackLimitation());
        }
        if (pBean.getType().equals(EPayloadType.AOE_ATTACKED.toString())){
            Intent lIntent = new Intent(INTENT_TYPE);
            lIntent.putExtra(TYPE, EPayloadType.AOE_ATTACKED.toString());
            lIntent.putExtra(PORTAL, pBean.getBuildingAttacked().getPortal());
            lIntent.putExtra(PLAYER, pBean.getBuildingAttacked().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

    }
}

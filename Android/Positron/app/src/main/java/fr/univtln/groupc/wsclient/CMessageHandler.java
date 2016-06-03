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
            Intent lIntent = new Intent("resonator_posed");
            lIntent.putExtra(TYPE, EPayloadType.RESONATOR_POSED.toString());
            lIntent.putExtra(PORTAL, pBean.getResonatorPosed().getPortal());
            lIntent.putExtra(PLAYER, pBean.getResonatorPosed().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        else if (pBean.getType().equals(EPayloadType.PORTAL_CHANGING_TEAM.toString())){
            Log.d("tag", "taille des reso du portal recu : " + pBean.getTeamPortalChanged().getPortal().getResonators().size());
            ObjectMapper lMapper = new ObjectMapper();
            String lPortalJson = null;
            String lPlayerJson = null;
            try {
                lPortalJson = lMapper.writeValueAsString(pBean.getTeamPortalChanged().getPortal());
                lPlayerJson = lMapper.writeValueAsString(pBean.getTeamPortalChanged().getPlayer());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Intent lIntent = new Intent("test");
            lIntent.putExtra(TYPE, "portal_changing_team");
            lIntent.putExtra(PORTAL, pBean.getTeamPortalChanged().getPortal());
            lIntent.putExtra(PLAYER, pBean.getTeamPortalChanged().getPlayer());
            mWebSocketService.sendBroadcast(lIntent);
        }

        else if (pBean.getType().equals(EPayloadType.LINK_CREATED.toString())){
            Intent lIntent = new Intent();
            lIntent.putExtra(TYPE, EPayloadType.LINK_CREATED.toString());
            lIntent.putExtra(EPayloadType.LINK_CREATED.toString(), pBean.getLinkCreated().getLink());
            mWebSocketService.sendBroadcast(lIntent);
        }

    }
}

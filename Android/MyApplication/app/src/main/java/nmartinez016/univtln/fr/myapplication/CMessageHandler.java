package nmartinez016.univtln.fr.myapplication;

import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.MessageHandler;

import fr.univtln.groupc.CPayloadBean;
import fr.univtln.groupc.EPayloadType;

/**
 * Created by marti on 02/06/2016.
 */
public class CMessageHandler implements MessageHandler.Whole<CPayloadBean> {

    private WebSocketService mWebSocketService;
    public static String TYPE = "type";

    public CMessageHandler(WebSocketService pService){
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
            Intent lIntent = new Intent("resonator_posed");
            lIntent.putExtra(TYPE, EPayloadType.RESONATOR_POSED.toString());
            lIntent.putExtra(EPayloadType.RESONATOR_POSED.toString(), pBean.getResonatorPosed().getPortal());
            mWebSocketService.sendBroadcast(lIntent);
        }

        else if (pBean.getType().equals(EPayloadType.PORTAL_CHANGING_TEAM.toString())){
            Log.d("tag", "taille des reso du portal recu : " + pBean.getTeamPortalChanged().getPortal().getResonators().size());
            Intent lIntent = new Intent();
            lIntent.putExtra(TYPE, EPayloadType.TEAM_PORTAL_CHANGED.toString());
            lIntent.putExtra(EPayloadType.TEAM_PORTAL_CHANGED.toString(), pBean.getTeamPortalChanged().getPortal());
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

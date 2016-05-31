package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CSkillEntity;


/**
 * Created by toms on 5/3/16.
 */
public class CRestGet extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... params) {
        String lUrlString = params[0]; // URL to call
        String lResultToDisplay = "";
        InputStream lIn = null;
        String lJson = "";
        try {
            URL lUrl = new URL(lUrlString);
            HttpURLConnection urlConnection = (HttpURLConnection) lUrl.openConnection();
            lIn = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader lReader = new BufferedReader(new InputStreamReader(lIn), 8);
            StringBuilder lSb = new StringBuilder();
            String lLine = null;
            while ((lLine = lReader.readLine()) != null) {
                lSb.append(lLine + "\n");
            }
            lIn.close();
            lJson = lSb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return lJson;
    }

    /**
     * get all portals from database to display them on Android Map
     * @return
     */
    public List<CPortalEntity> getPortalsRest(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        String lUrlString = CRest.API_URL + "/portals";
        Log.d("test", "->-> " + lUrlString);
        String lPortalsJson = null;
        List<CPortalEntity> lPortals = new ArrayList<>();
        JSONObject lPortalObject = null;
        try {
            Log.d("test8","get portals :");
            lPortalsJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", "deserialisation !\n" + lPortalsJson);

            lPortals = lMapper.readValue(lPortalsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
            for (CPortalEntity lPortalTest : lPortals){
                for (CResonatorEntity lResonatorTest : lPortalTest.getResonators()){
                    Log.d("test", "niveau de resonateur : " + lResonatorTest.getLevel());
                }
            }
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
            //Log.d("test", "objects numero 1 ->\n" + lPortals.get(0).getObjects().get(0));
            //Log.d("test", "objects numero 2 ->\n" + lPortals.get(0).getObjects().get(1));

            Log.d("test8", "deserialized!!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());

        } catch (JsonParseException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("hello" + lPortals);
        Log.d("test8", " tous les portails ->\n " + lPortals);
        return lPortals;
    }

    /**
     * get players
     * @return
     */
    public List<CPlayerEntity> getPlayersRest(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lUrlString = CRest.API_URL + "/players";
        Log.d("test", "->-> " + lUrlString);
        String lPlayersJson = null;
        List<CPlayerEntity> lPlayers = null;
        try {
            Log.d("test","get players");
            lPlayersJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPlayersJson);
            lPlayers = lMapper.readValue(lPlayersJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPlayerEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("hello" + lPortals);
        return lPlayers;
    }

    /**
     * get player by id from database
     * @return
     */
    public CPlayerEntity getPlayerByID(int pId){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/players/"+Integer.toString(pId);
        Log.d("test", "->-> " + lUrlString);
        String lPlayerJson = null;
        CPlayerEntity lPlayer = null;
        try {
            Log.d("test", "salut ?");
            lPlayerJson = new CRestGet().execute(lUrlString).get();
            Log.d("test", lPlayerJson);
            System.out.println(" -> la dedans ?");
            lPlayer = lMapper.readValue(lPlayerJson, CPlayerEntity.class);
            System.out.println("-> !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("test", "-> player object :\n  " + lPlayer);
        return lPlayer;
    }



    /**
     * get portals by id from database
     * @return
     */
    public CPortalEntity getPortalByIdRest(int pId){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/portals/"+Integer.toString(pId);
        Log.d("test", "->-> " + lUrlString);
        String lPortalJson = null;
        CPortalEntity lPortal = null;
        try {
            Log.d("test", "salut ?");
            lPortalJson = new CRestGet().execute(lUrlString).get();
            Log.d("test", lPortalJson);
            System.out.println(" -> la dedans ?");
            lPortal = lMapper.readValue(lPortalJson, CPortalEntity.class);
            System.out.println("-> !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lPortal);
        return lPortal;
    }


    public List<CResonatorEntity> getResonatorsByPortalRest(int pId){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        String lUrlString = CRest.API_URL + "/resonators" + "/portals/" + Integer.toString(pId);
        Log.d("test", "->-> " + lUrlString);
        String lResonatorsJson = null;
        List<CResonatorEntity> lResonators = new ArrayList<>();
        JSONObject lPortalObject = null;
        try {
            Log.d("test","get Resonators by Portals :");
            lResonatorsJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPortalsJson);
            lResonators = lMapper.readValue(lResonatorsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CResonatorEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
            //Log.d("test", "objects numero 1 ->\n" + lPortals.get(0).getObjects().get(0));
            //Log.d("test", "objects numero 2 ->\n" + lPortals.get(0).getObjects().get(1));

            Log.d("test", "deserialized!!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());

        } catch (JsonParseException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("hello" + lPortals);
        Log.d("test", lResonators.toString());
        return lResonators;
    }


    public List<CResonatorEntity> getResonatorsByPortalAndTeamRest(int pId1,int pId2){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        String lUrlString = CRest.API_URL + "/resonators" + "/portals" + "/teams" + "/"+Integer.toString(pId1) +"/"+ Integer.toString(pId2);
        Log.d("test", "->-> " + lUrlString);
        String lResonatorsJson = null;
        List<CResonatorEntity> lResonators = new ArrayList<>();
        JSONObject lPortalObject = null;
        try {
            Log.d("test","get Resonators by Portals :");
            lResonatorsJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPortalsJson);
            lResonators = lMapper.readValue(lResonatorsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CResonatorEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
            //Log.d("test", "objects numero 1 ->\n" + lPortals.get(0).getObjects().get(0));
            //Log.d("test", "objects numero 2 ->\n" + lPortals.get(0).getObjects().get(1));

            Log.d("test", "deserialized!!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());

        } catch (JsonParseException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("hello" + lPortals);
        Log.d("test", lResonators.toString());
        return lResonators;
    }

    public List<CKeyEntity> getKeysByPlayerRest(CPlayerEntity pPlayer){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lUrlString = CRest.API_URL + "/keys/players/" + Integer.toString(pPlayer.getId());
        Log.d("test", "->-> " + lUrlString);
        String lKeysJson = null;
        List<CKeyEntity> lKeys = null;
        try {
            Log.d("test","get keys by player");
            lKeysJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPlayersJson);
            lKeys = lMapper.readValue(lKeysJson, lMapper.getTypeFactory().constructCollectionType(List.class, CKeyEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("hello" + lPortals);
        return lKeys;
    }


    public CLinkEntity getLinkByID(int pId){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/links/"+Integer.toString(pId);
        String lLinkJson = null;
        CLinkEntity lLink = null;
        try {
            lLinkJson = new CRestGet().execute(lUrlString).get();
            System.out.println(" -> la dedans ?");
            lLink = lMapper.readValue(lLinkJson, CLinkEntity.class);
            System.out.println("-> !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return lLink;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }




    /**
     * get player by id from database
     * @return
     */
    public CSkillEntity getSkillByID(int pId){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/skills/"+Integer.toString(pId);
        Log.d("test", "->-> " + lUrlString);
        String lSkillJson = null;
        CSkillEntity lSkill = null;
        try {
            Log.d("test", "salut ?");
            lSkillJson = new CRestGet().execute(lUrlString).get();
            Log.d("test", lSkillJson);
            System.out.println(" -> la dedans ?");
            lSkill = lMapper.readValue(lSkillJson, CSkillEntity.class);
            System.out.println("-> !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("test", "-> skill object :\n  " + lSkill);
        return lSkill;
    }



// TODO a vérifier
    /**
     * get all fields from database to display them on Android Map
     * @return
     */
    public List<CFieldEntity> getFieldsRest(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        String lUrlString = CRest.API_URL + "/fields";
        Log.d("test", "->-> " + lUrlString);
        String lFieldsJson = null;
        List<CFieldEntity> lFields = new ArrayList<>();
        JSONObject lFieldObject = null;
        try {
            Log.d("test8","get fields :");
            lFieldsJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", "deserialisation !\n" + lfieldsJson);

            lFields = lMapper.readValue(lFieldsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CFieldEntity.class));
   /*         for (CFieldEntity lFieldTest : lFields){
                for (CResonatorEntity lResonatorTest : lFieldTest.getResonators()){
                    Log.d("test", "niveau de resonateur : " + lResonatorTest.getLevel());
                }
            }
    */

            Log.d("test8", "deserialized!!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());

        } catch (JsonParseException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("test8", " tous les portails ->\n " + lFields);
        return lFields;
    }
}

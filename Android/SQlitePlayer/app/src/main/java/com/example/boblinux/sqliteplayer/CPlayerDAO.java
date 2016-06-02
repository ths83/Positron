package com.example.boblinux.sqliteplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import fr.univtln.groupc.entities.CPlayerEntity;

/**
 * Created by boblinux on 01/06/16.
 */
public class CPlayerDAO implements IDAO<CPlayerEntity> {

    private SQLiteDatabase bdd;
    private CBDDSqlite mBddSqlite;
    private static final String NOM_BDD = "player.db";
    private static final int VERSION_BDD = 1;

    private static final String TABLE_PLAYER = "table_player";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;

    // NAME COLUMNS
    private static final String COL_Nickname = "nickname";
    private static final int NUM_COL_Nickname = 1;

    private static final String COL_Email ="email";
    private static final int NUM_COL_Email = 2;

    private static final String COL_Team ="team";
    private static final int NUM_COL_Team  = 3;

    private static final String COL_Xp ="xp";
    private static final int NUM_COL_Xp = 3;

    private static final String COL_BagSize ="bag_size";
    private static final int NUM_COL_BagSize = 4;

    private static final String COL_Longitude ="longitude";
    private static final int NUM_COL_Longitude = 5;

    private static final String COL_Latitude ="latitude";
    private static final int NUM_COL_Latitude= 6;

    private static final String COL_Energy ="energy";
    private static final int NUM_COL_Energy = 7;

    private static final String COL_EnergyMax ="energy_max";
    private static final int NUM_COL_EnergyMax = 8;

    private static final String COL_Skills ="skills";
    private static final int NUM_COL_Skills = 10;

    private static final String COL_Objects ="objects";
    private static final int NUM_COL_Objects = 11;


    public CPlayerDAO(Context context){
            //On créer la BDD et sa table
            mBddSqlite = new CBDDSqlite(context, NOM_BDD, null, VERSION_BDD);
        }

        public void open(){
            //on ouvre la BDD en écriture
            bdd = mBddSqlite.getWritableDatabase();
        }

        public void close(){
            //on ferme l'accès à la BDD
            bdd.close();
        }

        public SQLiteDatabase getBDD(){
            return bdd;
        }

        @Override
        public CPlayerEntity find(String pMail) throws Exception {
            //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
            Cursor c = bdd.query(TABLE_PLAYER, new String[] {COL_ID, COL_Nickname, COL_Email,
                    COL_Xp, COL_BagSize, COL_Longitude,
                    COL_Latitude, COL_Energy, COL_EnergyMax,},
                    COL_Email + " LIKE \"" + pMail +"\"", null, null, null, null, null);

            return cursorToSms(c);
        }

        @Override
        public List<CPlayerEntity> findAll() throws Exception {
            return null;
        }

    @Override
    public void persist(CPlayerEntity t) throws Exception {

        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID,t.getId());
        values.put(COL_Email,t.getEmail());
        values.put(COL_Nickname, t.getNickName());
        values.put(COL_Xp, t.getXp());
        values.put(COL_BagSize, t.getBagSize());
        values.put(COL_Longitude, t.getLong());
        values.put(COL_Latitude, t.getLat());
        values.put(COL_Energy, t.getEnergy());
        values.put(COL_EnergyMax, t.getEnergyMax());

        //on insère l'objet dans la BDD via le ContentValues
            bdd.insert(TABLE_PLAYER, null, values);
        }

    @Override
    public void remove(CPlayerEntity t) throws Exception {

        }
    @Override
    public void update(CPlayerEntity s, CPlayerEntity t) throws Exception {

    }

    @Override
    public void refresh(CPlayerEntity t) throws Exception {
        }

    //Cette méthode permet de convertir un cursor en un player
    private CPlayerEntity cursorToSms(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un player
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor


        Log.i("sqlite", "mail "+ c.getString(NUM_COL_Email));
        Log.i("sqlite", "name "+ c.getString(NUM_COL_Nickname));
        Log.i("sqlite","xp " + c.getString(NUM_COL_Xp));
        Log.i("sqlite","xp parsed " + Integer.parseInt(c.getString(NUM_COL_Xp)));
        Log.i("sqlite", "c.getString(NUM_COL_Longitude)) : "+ c.getString(NUM_COL_Longitude));
        Log.i("sqlite", "Double.parseDouble(c.getString(NUM_COL_Longitude)) : "+ Double.parseDouble(c.getString(NUM_COL_Longitude)));

        Log.i("sqlite", "c.getString(NUM_COL_Lat)) : "+ c.getString(NUM_COL_Latitude));
        Log.i("sqlite", "Double.parseDouble(c.getString(NUM_COL_Lat)) : "+ Double.parseDouble(c.getString(NUM_COL_Latitude)));


        CPlayerEntity mPlayerEntity = new CPlayerEntity.CPlayerBuilder(Integer.parseInt(c.getString(NUM_COL_ID)))
                .email(c.getString(NUM_COL_Email)).nickname(c.getString(NUM_COL_Nickname))
                .xp(Integer.parseInt(c.getString(NUM_COL_Xp))).bagSize(Integer.parseInt(c.getString(NUM_COL_BagSize)))
                .longitude(Double.parseDouble(c.getString(NUM_COL_Longitude))).latitude(Double.parseDouble(c.getString(NUM_COL_Latitude)))
                .energy(Integer.parseInt(c.getString(NUM_COL_Energy))).energyMax(Integer.parseInt(c.getString(NUM_COL_EnergyMax)))
                .build();
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return mPlayerEntity;
        }
    }


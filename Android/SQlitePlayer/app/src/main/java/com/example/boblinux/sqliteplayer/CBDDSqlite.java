package com.example.boblinux.sqliteplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by boblinux on 01/06/16.
 */
public class CBDDSqlite extends SQLiteOpenHelper{
    // ID
    private static final String COL_ID = "ID";
    // NAME TABLE
    private static final String TABLE_PLAYER = "table_player";

    // NAME COLUMNS
    private static final String COL_Nickname = "nickname";
    private static final String COL_Email ="email";
    private static final String COL_Team ="team";
    private static final String COL_Xp ="xp";
    private static final String COL_BagSize ="bag_size";
    private static final String COL_Longitude ="longitude";
    private static final String COL_Latitude ="latitude";
    private static final String COL_Energy ="energy";
    private static final String COL_EnergyMax ="energy_max";
    private static final String COL_Skills ="skills";
    private static final String COL_Objects ="objects";


    private static final String CREATE_BDD_PLAYER = "CREATE TABLE " + TABLE_PLAYER + " ("
            + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_Nickname + " TEXT, "+ COL_Email + " TEXT, "
            + COL_Xp + " INTEGER, "+ COL_BagSize + " INTEGER, "+ COL_Longitude + " DOUBLE, "
            + COL_Latitude + " DOUBLE, "+ COL_Energy + " INTEGER, "+ COL_EnergyMax + " INTEGER);";

    public CBDDSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER + ";");
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SMS_RECEIVED + ";");
        onCreate(db);
    }

    }



package com.example.ivan.webview.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


public class ConexionBD extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "llamadas.sqlite";
    public static final int DATABASE_VERSION = 1;

    public ConexionBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)
        String sql;
        sql="create table "+ TablaLlamada.TABLA+
                " ("+
                TablaLlamada._ID + " integer primary key autoincrement, "+
                TablaLlamada.NUMERO+" integer, "+
                TablaLlamada.DIA+" integer, "+
                TablaLlamada.DIAM+" integer," +
                TablaLlamada.MES+" integer, "+
                TablaLlamada.AÑO+" integer "+
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists " + TablaLlamada.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }

    public static abstract class TablaLlamada implements BaseColumns {
        public static final String TABLA = "llamada";
        public static final String NUMERO = "numero";
        public static final String DIA = "dia";
        public static final String DIAM = "diam";
        public static final String MES = "mes";
        public static final String AÑO = "año";

    }

}

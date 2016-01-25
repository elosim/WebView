package com.example.ivan.webview.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ivan.webview.pojo.Llamada;

import java.util.ArrayList;
import java.util.List;


public class GestorLlamadas {
    private ConexionBD abd;
    private SQLiteDatabase bd;

    public GestorLlamadas(Context c){
        abd = new ConexionBD(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void openRead() {
        bd = abd.getReadableDatabase();
    }
    public void close() {
        abd.close();
    }

    public long insert(Llamada p) {
        ContentValues valores = new ContentValues();
        valores.put(ConexionBD.TablaLlamada.DIA, p.getDia());
        valores.put(ConexionBD.TablaLlamada.DIAM, p.getDiasemana());
        valores.put(ConexionBD.TablaLlamada.MES, p.getMes());
        valores.put(ConexionBD.TablaLlamada.AÑO, p.getAño());

        long id = bd.insert(ConexionBD.TablaLlamada.TABLA, null, valores);
        return id;
    }

    public int delete(Llamada m) {
        return delete(m.getId());
    }

    public int delete(long id){
        String condicion = ConexionBD.TablaLlamada._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(ConexionBD.TablaLlamada.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Llamada m){
//        ContentValues valores = new ContentValues();
//        valores.put(Contrato.TablaIngredientes.NOMBRE, m.getNombre());
//        valores.put(Contrato.TablaIngredientes._ID, m.getId());
//        String condicion = Contrato.TablaIngredientes._ID + " = ?";
//        String[] argumentos = { m.getId() + "" };
//        int cuenta = bd.update(Contrato.TablaIngredientes.TABLA, valores,condicion, argumentos);
        return 0;
    }

    public Llamada getRow(Cursor c) {
        Llamada p = new Llamada();
        p.setId(c.getLong(c.getColumnIndex(ConexionBD.TablaLlamada._ID)));
        p.setDia((c.getLong(c.getColumnIndex(ConexionBD.TablaLlamada.DIA))));
        p.setDiasemana(c.getLong(c.getColumnIndex(ConexionBD.TablaLlamada.DIAM)));
        p.setMes(c.getLong(c.getColumnIndex(ConexionBD.TablaLlamada.MES)));
        p.setAño(c.getLong(c.getColumnIndex(ConexionBD.TablaLlamada.AÑO)));
        return p;
    }

    public Llamada getRow(long id) {
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    //REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEVIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIISAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(ConexionBD.TablaLlamada.TABLA, null, condicion, parametros, null, null, ConexionBD.TablaLlamada._ID+", "+ConexionBD.TablaLlamada.DIA+", "+ ConexionBD.TablaLlamada.DIAM+", "+ ConexionBD.TablaLlamada.MES+", "+ ConexionBD.TablaLlamada.AÑO);


        return cursor;
    }

    public List<Llamada> select(String condicion, String[] parametros) {
        List<Llamada> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Llamada p;
        while (cursor.moveToNext()) {
            p = getRow(cursor);
            la.add(p);
        }
        cursor.close();
        return la;
    }

    public List<Llamada> select() {
        return select(null,null);
    }
}

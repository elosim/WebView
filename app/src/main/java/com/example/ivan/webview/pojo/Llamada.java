package com.example.ivan.webview.pojo;

/**
 * Created by ivan on 1/25/2016.
 */
public class Llamada {
    private long numero, id, dia, diasemana, mes, año;

    private String [] arrDW = new String[]{"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    private String [] arrM = new String[]{"Enero","" + "Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    public Llamada(long numero, long id, int diasemana, int dia, int mes, int año) {
        this.numero = numero;
        this.id = id;
        this.diasemana = diasemana;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public Llamada() {
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDia() {
        return dia;
    }

    public void setDia(long dia) {
        this.dia = dia;
    }

    public long getAño() {
        return año;
    }

    public void setAño(long año) {
        this.año = año;
    }

    public long getMes() {
        return mes;
    }

    public void setMes(long mes) {
        this.mes = mes;
    }

    public long getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(long diasemana) {
        this.diasemana = diasemana;
    }
    public String getRDiasemana(){
        return arrDW[((int) (this.diasemana - 1))];
    }
    public String getRMonth(){
        return arrM[((int) this.mes)];
    }
    @Override
    public String toString() {
        return "Llamada{" +
                "numero=" + numero +
                ", id=" + id +
                ", dia=" + dia +
                ", diasemana=" + diasemana +
                ", mes=" + mes +
                ", año=" + año +
                '}';
    }

}

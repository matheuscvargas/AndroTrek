package com.trekking.androtrek;

public class cConfig {

    //private variables
    int _id, _passo, _ajustehora, _horalargada;
 
    // Empty constructor
    public cConfig(){
 
    }
    // constructor
    public cConfig(int id, int passo, int ajusteHora, int horalargada){
        this._id = id;
        this._passo = passo;
        this._ajustehora = ajusteHora;
        this._horalargada = horalargada;
    }
 
    // constructor
    public cConfig(int passo, int ajusteHora, int horalargada){
        this._passo = passo;
        this._ajustehora = ajusteHora;
        this._horalargada = horalargada;
    }
    // getting and setting ID
    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }
 
    // getting and setting passo
    public int getPasso(){
        return this._passo;
    }
    public void setPasso(int passo){
        this._passo = passo;
    }
 
    // getting and setting Ajuste Hora
    public int getAjusteHora(){
        return this._ajustehora;
    }
    public void setAjusteHora(int ajusteHora){
        this._ajustehora = ajusteHora;
    }

    // getting and setting Hora Largada
    public int getHoraLargada(){
    	return this._horalargada;
    }
    public void setHoraLargada(int horalargada){
    	this._horalargada = horalargada;
    }
}

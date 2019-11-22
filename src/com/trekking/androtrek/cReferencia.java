package com.trekking.androtrek;

public class cReferencia {

    //private variables
    int _id;
    double _trecho, _velocidade, _distancia, _distancia_trecho, _distancia_prova, _tempo, _neutro;
    int _veloc_omite, _dist_omite, _temp_omite;
 
    // Empty constructor
    public cReferencia(){
 
    }
    // constructor Completo
    public cReferencia(int id, double trecho, double velocidade, double distancia, double distancia_trecho, double distancia_prova, double neutro, double tempo, int veloc_omite, int dist_omite, int temp_omite){
        this._id = id;
        this._trecho = trecho;
        this._velocidade = velocidade;
        this._distancia = distancia;
		this._distancia_trecho = distancia_trecho;
        this._distancia_prova = distancia_prova;
		this._neutro = neutro;
        this._tempo = tempo;
        this._veloc_omite = veloc_omite;
        this._dist_omite = dist_omite;
        this._temp_omite = temp_omite;
    }
    // constructor Velocidade e Dist�ncia omitida
    public cReferencia(double trecho, double velocidade, double distancia, double distancia_trecho, double distancia_prova, double tempo, int veloc_omite, int dist_omite){
        this._trecho = trecho;
        this._velocidade = velocidade;
        this._distancia = distancia;
		this._distancia_trecho = distancia_trecho;
        this._distancia_prova = distancia_prova;
        this._tempo = tempo;
        this._veloc_omite = veloc_omite;
        this._dist_omite = dist_omite;
    }
    // constructor Velocidade e Dist�ncia
    public cReferencia(double trecho, double velocidade, double distancia, double distancia_trecho, double distancia_prova, double tempo){
        this._trecho = trecho;
        this._velocidade = velocidade;
        this._distancia = distancia;
		this._distancia_trecho = distancia_trecho;
        this._distancia_prova = distancia_prova;
        this._tempo = tempo;
    }
    // constructor Neutro omitido
    public cReferencia(double trecho, double distancia_prova, double neutro, double tempo, int temp_omite){
        this._trecho = trecho;
        this._distancia_prova = distancia_prova;
        this._tempo = tempo;
        this._neutro = neutro;
        this._temp_omite = temp_omite;
    }
    // constructor Neutro
    public cReferencia(double trecho, double distancia_prova, double neutro, double tempo){
        this._trecho = trecho;
        this._distancia_prova = distancia_prova;
        this._tempo = tempo;
        this._neutro = neutro;
    }

    // getting and setting ID
    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }
 
    // getting and setting trecho
    public double getTrecho(){
        return this._trecho;
    }
    public void setTrecho(double trecho){
        this._trecho = trecho;
    }
 
    // getting and setting velocidade
    public double getVelocidade(){
        return this._velocidade;
    }
    public void setVelocidade(double velocidade){
        this._velocidade = velocidade;
    }

    // getting and setting distancia
    public double getDistancia(){
        return this._distancia;
    }
    public void setDistancia(double distancia){
        this._distancia = distancia;
    }

	// getting and setting distancia_trecho
	public double getDistanciaTrecho(){
		return this._distancia_trecho;
	}
	public void setDistanciaTrecho(double distancia_trecho){
		this._distancia_trecho = distancia_trecho;
	}
	
    // getting and setting distancia_prova
    public double getDistanciaProva(){
        return this._distancia_prova;
    }
    public void setDistanciaProva(double distancia_prova){
        this._distancia_prova = distancia_prova;
    }
    
    // getting and setting tempo
    public double getTempo(){
        return this._tempo;
    }
    public void setTempo(double tempo){
        this._tempo = tempo;
    }
    
    // getting and setting neutro
    public double getNeutro(){
        return this._neutro;
    }
    public void setNeutro(double neutro){
        this._neutro = neutro;
    }
    
    // getting and setting velocidade omitida
    public int getVelocOmite(){
        return this._veloc_omite;
    }
    public void setVelocOmite(int veloc_omite){
        this._veloc_omite = veloc_omite;
    }
    
    // getting and setting distancia omitida
    public int getDistOmite(){
        return this._dist_omite;
    }
    public void setDistOmite(int dist_omite){
        this._dist_omite = dist_omite;
    }
    
    // getting and setting tempo omitida
    public int getTempOmite(){
        return this._temp_omite;
    }
    public void setTempOmite(int temp_omite){
        this._temp_omite = temp_omite;
    }
}

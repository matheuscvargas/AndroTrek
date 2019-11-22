package com.trekking.androtrek;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import java.util.*;

public class DBHandler extends SQLiteOpenHelper{
	
    // All Static variables
    private static final int DATABASE_VERSION = 1; // Database Version
    private static final String DATABASE_NAME = "NavegadorLT"; // Database Name
    
    // Tabela Configura��es
    private static final String TABLE_CONFIG = "config";

    private static final String KEY_ID = "_id";
    private static final String KEY_PASSO = "passo";
    private static final String KEY_AJ_HR = "ajustehora";
    private static final String KEY_HR_LARGADA = "hora_largada";

    // Tabela Refer�ncias
    public static final String TABLE_REFERENCIAS = "referencias";

    private static final String REFS_KEY_ID = "_id";
    private static final String REFS_TRECHO = "trecho";
    private static final String REFS_VELOC = "velocidade";
    private static final String REFS_DIST = "distancia";
	private static final String REFS_DIST_TRECHO = "distancia_trecho";
    private static final String REFS_DIST_PROVA = "distancia_prova";
    public static final String REFS_NEUTRO = "neutro";
    private static final String REFS_TEMPO = "tempo";
    private static final String REFS_BOOL_VELOC_OMITE = "veloc_omite";
    private static final String REFS_BOOL_DIST_OMITE = "dist_omite";
    private static final String REFS_BOOL_TEMP_OMITE = "temp_omite";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//		super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
        String CREATE_CONFIG_TABLE = "CREATE TABLE " + TABLE_CONFIG + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
        		+ KEY_PASSO + " INTEGER,"
                + KEY_AJ_HR + " INTEGER,"
        		+ KEY_HR_LARGADA + " INTEGER"
                + ")";
        
        String CREATE_REFS_TABLE = "CREATE TABLE " + TABLE_REFERENCIAS + "("
                + REFS_KEY_ID + " INTEGER PRIMARY KEY,"
        		+ REFS_TRECHO + " REAL,"
                + REFS_VELOC + " REAL," 
        		+ REFS_DIST + " REAL,"
				+ REFS_DIST_TRECHO + " REAL,"
        		+ REFS_DIST_PROVA + " REAL,"
        		+ REFS_NEUTRO + " REAL,"
        		+ REFS_TEMPO + " REAL,"
        		+ REFS_BOOL_VELOC_OMITE + " INTEGER,"
        		+ REFS_BOOL_DIST_OMITE + " INTEGER,"
        		+ REFS_BOOL_TEMP_OMITE + " INTEGER"
        		+ ")";
        
        db.execSQL(CREATE_CONFIG_TABLE);
        db.execSQL(CREATE_REFS_TABLE);
        
        ContentValues values = new ContentValues();
        values.put(KEY_PASSO, 100);
        values.put(KEY_AJ_HR, 0);
        values.put(KEY_HR_LARGADA, 32400);
        db.insert(TABLE_CONFIG, null, values);
//        db.close(); // Closing database connection
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONFIG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFERENCIAS);
 
        // Create tables again
        onCreate(db);
	}
	
    /** All CRUD(Create, Read, Update, Delete) Operations **/
	/** ----- CRUD para a tabela CONFIG ----- **/
	
    // Getting single register
    cConfig getregister(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_CONFIG, new String[] { KEY_ID,
        		KEY_PASSO, KEY_AJ_HR, KEY_HR_LARGADA }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        cConfig config = new cConfig(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
        return config;
    }
 
    // Updating single register
    public int updateRegister(cConfig config) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_PASSO, config.getPasso());
        values.put(KEY_AJ_HR, config.getAjusteHora());
        values.put(KEY_HR_LARGADA, config.getHoraLargada());
 
        // updating row
        return db.update(TABLE_CONFIG, values, KEY_ID + " = ?",
                new String[] { String.valueOf(config.getID()) });
    }
 
    // Apagar tabela
    public void deleteTableConfig(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_CONFIG, null, null);
    	db.close();
    }
    
    
    /** ----- CRUD para a tabela REFERENCIAS ----- **/
    // Adding new register
    public void addReferencia(cReferencia referencia) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(REFS_TRECHO, referencia.getTrecho());
        values.put(REFS_VELOC, referencia.getVelocidade());
        values.put(REFS_DIST, referencia.getDistancia());
		values.put(REFS_DIST_TRECHO, referencia.getDistanciaTrecho());
        values.put(REFS_DIST_PROVA, referencia.getDistanciaProva());
        values.put(REFS_NEUTRO, referencia.getNeutro());
        values.put(REFS_TEMPO, referencia.getTempo());
        values.put(REFS_BOOL_VELOC_OMITE, referencia.getVelocOmite());
        values.put(REFS_BOOL_DIST_OMITE, referencia.getDistOmite());
        values.put(REFS_BOOL_TEMP_OMITE, referencia.getTempOmite());
 
        // Inserting Row
        db.insert(TABLE_REFERENCIAS, null, values);
        db.close(); // Closing database connection
    }
    
    // Apagar tabela
    public void deleteTableRefs(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_REFERENCIAS, null, null);
//    	db.close();
    }
	
	// Deleting single register
    public void deleteReferencia(cReferencia referencia) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REFERENCIAS, REFS_KEY_ID + " = ?",
				  new String[] { String.valueOf(referencia.getID()) });
        db.close();
    }
    
    public void deleteReferenciaByID(int referencia){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REFERENCIAS, REFS_KEY_ID + " = ?",
				  new String[] { String.valueOf(referencia) });
        db.close();
    }

    // Getting register
    public cReferencia getReferenciaByID(int id) {
    	SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_REFERENCIAS, null, REFS_KEY_ID + " = ?", new String[] { String.valueOf(id)},null, null, null, null);

		cursor.moveToFirst();
    	cReferencia referencia = new cReferencia();
    	referencia.setID(Integer.parseInt(cursor.getString(0)));
    	referencia.setTrecho(Double.parseDouble(cursor.getString(1)));
    	referencia.setVelocidade(Double.parseDouble(cursor.getString(2)));
    	referencia.setDistancia(Double.parseDouble(cursor.getString(3)));
		referencia.setDistanciaTrecho(Double.parseDouble(cursor.getString(4)));
    	referencia.setDistanciaProva(Double.parseDouble(cursor.getString(5)));
    	referencia.setNeutro(Double.parseDouble(cursor.getString(6)));
    	referencia.setTempo(Double.parseDouble(cursor.getString(7)));
    	referencia.setVelocOmite(Integer.parseInt(cursor.getString(8)));
    	referencia.setDistOmite(Integer.parseInt(cursor.getString(9)));
    	referencia.setTempOmite(Integer.parseInt(cursor.getString(10)));
    	
    	return referencia;
    }
    
	// Getting All registers
    public List<cReferencia> getAllReferencias() {
        List<cReferencia> referenciaList = new ArrayList<cReferencia>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REFERENCIAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	cReferencia referencia = new cReferencia();
            	referencia.setID(Integer.parseInt(cursor.getString(0)));
            	referencia.setTrecho(Double.parseDouble(cursor.getString(1)));
            	referencia.setVelocidade(Double.parseDouble(cursor.getString(2)));
            	referencia.setDistancia(Double.parseDouble(cursor.getString(3)));
				referencia.setDistanciaTrecho(Double.parseDouble(cursor.getString(4)));
            	referencia.setDistanciaProva(Double.parseDouble(cursor.getString(5)));
            	referencia.setNeutro(Double.parseDouble(cursor.getString(6)));
            	referencia.setTempo(Double.parseDouble(cursor.getString(7)));
            	referencia.setVelocOmite(Integer.parseInt(cursor.getString(8)));
            	referencia.setDistOmite(Integer.parseInt(cursor.getString(9)));
            	referencia.setTempOmite(Integer.parseInt(cursor.getString(10)));
            	
                // Adding contact to list
                referenciaList.add(referencia);
            } while (cursor.moveToNext());
        }
        return referenciaList;
    }
	
	public Cursor getAllCursor() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REFERENCIAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
		return cursor;
	}
	
	// Updating a register
    public int updateReferencia(cReferencia referencia) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REFS_TRECHO, referencia.getTrecho());
        values.put(REFS_VELOC, referencia.getVelocidade());
        values.put(REFS_DIST, referencia.getDistancia());
		values.put(REFS_DIST_TRECHO, referencia.getDistanciaTrecho());
        values.put(REFS_DIST_PROVA, referencia.getDistanciaProva());
        values.put(REFS_NEUTRO, referencia.getNeutro());
        values.put(REFS_TEMPO, referencia.getTempo());
        values.put(REFS_BOOL_VELOC_OMITE, referencia.getVelocOmite());
        values.put(REFS_BOOL_DIST_OMITE, referencia.getDistOmite());
        values.put(REFS_BOOL_TEMP_OMITE, referencia.getTempOmite());

        return db.update(TABLE_REFERENCIAS, values, KEY_ID + " = ?",
						 new String[] { String.valueOf(referencia.getID()) });

    }
	
    // Insere um trecho
    public void InsereTrecho(boolean TrNew, int ref, cReferencia referencia) {
    	SQLiteDatabase db1 = this.getWritableDatabase();
		Cursor cursorAnterior = db1.query(TABLE_REFERENCIAS, null, REFS_KEY_ID + " <= ?", new String[] { String.valueOf(ref)},null, null, null, null);
		Cursor cursorPosterior = db1.query(TABLE_REFERENCIAS, null, REFS_KEY_ID + " > ?", new String[] { String.valueOf(ref)},null, null, null, null);
		
		List<cReferencia> referenciaListAnterior = new ArrayList<cReferencia>();
		List<cReferencia> referenciaListPosterior = new ArrayList<cReferencia>();
		
		if (cursorAnterior.moveToFirst()) {
            do {
            	cReferencia referenciaAux = new cReferencia();
            	referenciaAux.setID(Integer.parseInt(cursorAnterior.getString(0)));
            	referenciaAux.setTrecho(Double.parseDouble(cursorAnterior.getString(1)));
            	referenciaAux.setVelocidade(Double.parseDouble(cursorAnterior.getString(2)));
            	referenciaAux.setDistancia(Double.parseDouble(cursorAnterior.getString(3)));
				referenciaAux.setDistanciaTrecho(Double.parseDouble(cursorAnterior.getString(4)));
            	referenciaAux.setDistanciaProva(Double.parseDouble(cursorAnterior.getString(5)));
            	referenciaAux.setNeutro(Double.parseDouble(cursorAnterior.getString(6)));
            	referenciaAux.setTempo(Double.parseDouble(cursorAnterior.getString(7)));
            	referenciaAux.setVelocOmite(Integer.parseInt(cursorAnterior.getString(8)));
            	referenciaAux.setDistOmite(Integer.parseInt(cursorAnterior.getString(9)));
            	referenciaAux.setTempOmite(Integer.parseInt(cursorAnterior.getString(10)));

                // Adding contact to list
                referenciaListAnterior.add(referenciaAux);
            } while (cursorAnterior.moveToNext());
        }
		cursorAnterior.moveToLast();

		if (cursorPosterior.moveToFirst()) {
            do {
            	cReferencia referenciaAux = new cReferencia();
            	referenciaAux.setID(Integer.parseInt(cursorPosterior.getString(0)));
				if (TrNew) {
					if (Double.parseDouble(cursorPosterior.getString(1)) == Double.parseDouble(cursorAnterior.getString(1))) {
						referenciaAux.setTrecho(Double.parseDouble(cursorPosterior.getString(1)) + 2);
					}
					else {
						referenciaAux.setTrecho(Double.parseDouble(cursorPosterior.getString(1)) + 1);
					}
				}
				else {
					referenciaAux.setTrecho(Double.parseDouble(cursorPosterior.getString(1)));
				}
            	referenciaAux.setVelocidade(Double.parseDouble(cursorPosterior.getString(2)));
            	referenciaAux.setDistancia(Double.parseDouble(cursorPosterior.getString(3)));
				referenciaAux.setDistanciaTrecho(Double.parseDouble(cursorPosterior.getString(4)));
            	referenciaAux.setDistanciaProva(Double.parseDouble(cursorPosterior.getString(5)));
            	referenciaAux.setNeutro(Double.parseDouble(cursorPosterior.getString(6)));
            	referenciaAux.setTempo(Double.parseDouble(cursorPosterior.getString(7)));
            	referenciaAux.setVelocOmite(Integer.parseInt(cursorPosterior.getString(8)));
            	referenciaAux.setDistOmite(Integer.parseInt(cursorPosterior.getString(9)));
            	referenciaAux.setTempOmite(Integer.parseInt(cursorPosterior.getString(10)));

                // Adding contact to list
                referenciaListPosterior.add(referenciaAux);
            } while (cursorPosterior.moveToNext());
        }
		
		deleteTableRefs();
		
		for (cReferencia cn1 : referenciaListAnterior) {
			addReferencia(cn1);
		}

		addReferencia(referencia);
		
		for (cReferencia cn2 : referenciaListPosterior) {
			addReferencia(cn2);
		}
    }
    
    // Atualiza o BD
	public void atualizaBD(Cursor cursor){
		double Trecho, Distancia, DistanciaTrecho, DistanciaProva, Velocidade, segundos;
		
		Trecho = 0;
		Distancia = 0;
		DistanciaTrecho = 0;
		DistanciaProva = 0;
		Velocidade = 0;
		segundos = 0;
		
//		deleteTableRefs();
		
		if (cursor.moveToFirst()) {
			deleteTableRefs();
			
            do {
				if (Trecho != Double.parseDouble(cursor.getString(1))){
					DistanciaTrecho = 0;
				}
				Trecho = Double.parseDouble(cursor.getString(1));
				Velocidade = Double.parseDouble(cursor.getString(2));
				Distancia = Double.parseDouble(cursor.getString(3));
				DistanciaTrecho = DistanciaTrecho + Distancia;
				DistanciaProva = DistanciaProva + Distancia;
				if (Velocidade != 0) {
					segundos = ((Distancia/Velocidade)) * 60 + segundos;
				}
				else {
					segundos = Double.parseDouble(cursor.getString(6)) + segundos;
				}
				
				
            	cReferencia referencia = new cReferencia();
            	referencia.setTrecho(Trecho);
            	referencia.setVelocidade(Velocidade);
            	referencia.setDistancia(Distancia);
				referencia.setDistanciaTrecho(DistanciaTrecho);
            	referencia.setDistanciaProva(DistanciaProva);
            	referencia.setNeutro(Double.parseDouble(cursor.getString(6)));
            	referencia.setTempo(segundos);
            	referencia.setVelocOmite(Integer.parseInt(cursor.getString(8)));
            	referencia.setDistOmite(Integer.parseInt(cursor.getString(9)));
            	referencia.setTempOmite(Integer.parseInt(cursor.getString(10)));

                // Adding contact to list
                addReferencia(referencia);
            } while (cursor.moveToNext());
        }
	}
}

package com.solucionescomputacionales.cursoavanzado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.solucionescomputacionales.cursoavanzado.variablesglobales.LOG_TAG;

public class usuariosDB extends SQLiteOpenHelper {
    /// se crean las variables
    private static final int VERSION_BASEDATOS=1;
    public static final String NOMBRE_BASEDATOS="usuarios.db";
    public usuariosDB(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }


// SE EJECUTA PARA CREAR UNA SOLA VEZ
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       /// se utiliza para escribir el texto a ejecutart
        String comando ="CREATE TABLE " + usuariosContrato.usuariosColumnas.TABLE_NAME + " (" +
                usuariosContrato.usuariosColumnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                usuariosContrato.usuariosColumnas.NOMBRE + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.PASSWORD + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.GENERO + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.ROL + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.CORREO + " TEXT NOT NULL" +
                ")";
        try {
            sqLiteDatabase.execSQL(comando);
            Log.i(LOG_TAG,"Comando ejecutado en sql: " + comando);
            Log.i(LOG_TAG,"TABLA: " + usuariosContrato.usuariosColumnas.TABLE_NAME +
                    "Creada exitosamente!!");
        }catch (Exception e){
            Log.e(LOG_TAG,"Exception on db.execSql: " + e);
        }
    }
/// ELMETODO UPGRADE SE EJECUTA LA PRIMERA VEZ O CUANDO CAMVIA EL NUMERO DE VERSION
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOMBRE_BASEDATOS);
        onCreate(sqLiteDatabase);


    }
}

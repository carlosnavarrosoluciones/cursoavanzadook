package com.solucionescomputacionales.cursoavanzado;

import android.content.Context;
import android.content.SharedPreferences;

public class myData {
    private SharedPreferences preferences;
//cONSTRUCTOR DE LA CLASE myData, solicita el context para poder configurar preferences
    public myData(Context context) {
       /// se crea un archivo llamado sharedCursoAvanzado de modo privado
        preferences=context.getSharedPreferences("sharedCursoAvanzado",Context.MODE_PRIVATE);
    }
    public void guardarUsiario(String usuario){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("nombreUsuario",usuario);
        editor.apply();
    }
    public void setLogeo(Boolean access){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("login",access);
        editor.apply();
    }
    /// leer el usuario
    public String obtenerUsuario(){
        return preferences.getString("nombreUsuario","");
    }
    public boolean getLogeo(){
        return preferences.getBoolean("login",false);
    }
}

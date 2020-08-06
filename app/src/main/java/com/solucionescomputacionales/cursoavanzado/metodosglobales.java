package com.solucionescomputacionales.cursoavanzado;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class metodosglobales {
    /*
    Clase java donde estan todos los metodos globales de la aplicacion
     */
    public static String obtenetversionapp(Context contex){
        String respuesta="";
        try {
            PackageInfo packageInfo = contex.getPackageManager().getPackageInfo(contex.getPackageName(),0);
        respuesta = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return respuesta;
    }

}

package com.solucionescomputacionales.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.CAMERA;
import static com.solucionescomputacionales.cursoavanzado.metodosglobales.obtenetversionapp;

public class LoadActivity extends AppCompatActivity {

    ///////////////vistas////////////////////////
    TextView    texloadinfo,texloadversion;
    Button btnPermisos;
/////////////////////////////////////////////////////////

    ////UTILIDADES////////////////////////////////
    Context context;
    Timer primertimer,segundotimer;
    boolean permisocamara=false;

    ///////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        texloadinfo=findViewById(R.id.texloadinfo);
        texloadversion=findViewById(R.id.texloadversion);
        btnPermisos=findViewById(R.id.btnPermisos);
        context=LoadActivity.this;

        String version = obtenetversionapp(context);
                texloadversion.setText(version);
            revisarPermisos();

    }
    protected void onResume(){
        btnPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revisarPermisos();
                btnPermisos.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void revisarPermisos(){
        ///revisa permisos de camara
        permisocamara=checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED;
        if (permisocamara){
            //se inicia el primer timer
            setprimertimer(3000);
        }else{
            requestPermissions(new String[]{CAMERA},25);
        }
    }
////para que salga es onRequestPermissionResult
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==25){
            permisocamara=checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED;
            setprimertimer(3000);
        }else{
            texloadinfo.setText(getString(R.string.alertaPermisos));
            btnPermisos.setVisibility(View.VISIBLE);
        }
    }

    ////se crea codigo de timer
    private void setprimertimer(int milis){
        primertimer = new Timer();  //declara timer como nuevo
        primertimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ///aqui se ejecuta el codigo una vez quye el timer termina
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        /////aQUI NOS REGRESAMOS AL HILO PRINCIPAL
                        texloadinfo.setText(getString(R.string.preparando));
                        myData myData=new myData(context);////se puede omitir
                        boolean acceso=myData.getLogeo();/// se puede omitir
                        if(acceso){////se puede omitir
                            startActivity(new Intent(context,MainActivity.class));// se puede omitir
                        }else{///se puede omitir
                            startActivity(new Intent(context,LoginActivity.class));
                        }
                        //setsegundotimer(4000);

                    }
                });
            }
        },milis);
    }

    ////se crea codigo de timer
    private void setsegundotimer(int milis){
        segundotimer = new Timer();  //declara timer como nuevo
        segundotimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ///aqui se ejecuta el codigo una vez quye el timer termina
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        /////aQUI NOS REGRESAMOS AL HILO PRINCIPAL
                        texloadinfo.setText(getString(R.string.espere));
                    }
                });
            }
        },milis);
    }



}
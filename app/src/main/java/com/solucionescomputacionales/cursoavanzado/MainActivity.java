package com.solucionescomputacionales.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static com.solucionescomputacionales.cursoavanzado.metodosglobales.obtenetversionapp;
import static com.solucionescomputacionales.cursoavanzado.variablesglobales.codigoQR;


public class MainActivity extends AppCompatActivity {
//// VISTAS
    Toolbar maintoolBar;
    Menu menuActivity;
    Context context;
    Button btnLeerQR;
    TextView txtCodigoQr;
    ////objeto y utliidad para mostrar cuadro de dialogo
    AlertDialog acercaDeDialogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracionesIniciales();
    }
    private void configuracionesIniciales(){
        maintoolBar=findViewById(R.id.toolbarMain);
        btnLeerQR=findViewById(R.id.btnLeerQR);
        txtCodigoQr=findViewById(R.id.txtCodigoQR);
        /// se indica cual action bar se usa
        setSupportActionBar(maintoolBar);
       //para quitar el titulo del action Bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        context=MainActivity.this;
    }
    ///llamar los tres puntitos infla la vista del menu que diseñamos (main menu ademas se iguala el menuActivity a la vista inflada

    @Override
    protected void onResume() {
        super.onResume();
        btnLeerQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,QrActivity.class));
            }
        });
        if (!codigoQR.isEmpty()){///compueraba que el dato este vacio
            String codigoLeido=getString(R.string.codigoEscaneado)+ ""+codigoQR;
            txtCodigoQr.setText(codigoLeido);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        this.menuActivity=menu;
        return super.onCreateOptionsMenu(menu);

    }
///para cachar los eventos del menu desplegable
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId(); // obtenemos el id del item presionado por el usuario
        switch (id){
            case R.id.mainMenu_logout:
                ///BORRAR SHARE PREFERENCES Y ENVIA A VENTANA DE CSRGA
                myData myData = new myData(context);
                myData.setLogeo(false);
                startActivity(new Intent(context,LoadActivity.class));
                break;
            case  R.id.mainMenu_About:
               ///se llama al metodo que trae al cuadro de dialogo
                mostrarCuadroDialogoAcercaDe();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //// se crea el metodo que llama e infla al cuadro de dialogo
    private void mostrarCuadroDialogoAcercaDe(){
       AlertDialog.Builder builder=new AlertDialog.Builder(context);
        ViewGroup parent=findViewById(R.id.parent);
        View vista=getLayoutInflater().inflate(R.layout.acerca_de_dialogo,parent,false);
        TextView txtVersion=vista.findViewById(R.id.txtDialogoVersion);
        TextView txtSigueme=vista.findViewById(R.id.txtSigueme);
        Linkify.addLinks(txtSigueme,Linkify.WEB_URLS);//SE USA PARA HACER LINKS AL TEXTO AY QUE HABILITAR EN CLIKABLE A TRUE
        Button btnLlamar=vista.findViewById(R.id.btnLlamarVersion);///TODO falta codigo para realizar llamada
        txtVersion.setText(obtenetversionapp(context));
        builder.setView(vista);/// se configura la vista del buiolder
        acercaDeDialogo=builder.create(); // se crea el cuadro de dialogo apartir del builder
        acercaDeDialogo.show();// se muestra cuadro de dialogo
    }
}
package com.solucionescomputacionales.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
//// VISTAS
    Toolbar maintoolBar;
    Menu menuActivity;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracionesIniciales();
    }
    private void configuracionesIniciales(){
        maintoolBar=findViewById(R.id.toolbarMain);
        /// se indica cual action bar se usa
        setSupportActionBar(maintoolBar);
       //para quitar el titulo del action Bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        context=MainActivity.this;
    }
    ///llamar los tres puntitos infla la vista del menu que diseñamos (main menu ademas se iguala el menuActivity a la vista inflada

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
                Toast.makeText(this,"acerca de",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
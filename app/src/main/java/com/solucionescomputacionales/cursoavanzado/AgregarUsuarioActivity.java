package com.solucionescomputacionales.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.solucionescomputacionales.cursoavanzado.variablesglobales.usuariosApp;
import static com.solucionescomputacionales.cursoavanzado.metodosglobales.agregarUsuarioABaseDeDatos;

public class AgregarUsuarioActivity extends AppCompatActivity {
    //vistas
    EditText edtNombre, edtPassword, edtCorreo;
    RadioGroup radioSexo, radioRol;
    Button btnAgregar;
    //Utilidades
   Context context;
    //variables
    private String sexo = "";
    private String rol = "";
    private int id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        configuracionesIniciales();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString();
                String password = edtPassword.getText().toString();
                String correo = edtCorreo.getText().toString();
                if (!nombre.isEmpty()){
                    if (!password.isEmpty()){
                        if (!correo.isEmpty()){
                            if (correo.contains("@")){
                                if (!sexo.isEmpty()){
                                    if (!rol.isEmpty()){

                                        agregarUsuario(nombre,password,correo,sexo,rol);
                                    }else{
                                        mostrarToast("Seleccione el rol para continuar");
                                    }
                                }else{
                                    mostrarToast("Seleccione el genero para continuar");
                                }
                            }else{
                                mostrarToast("Ingrese un correo valido!");
                            }
                        }else{
                            mostrarToast("Favor de ingresar un correo para continuar!");
                        }
                    }else{
                        mostrarToast("Favor ingrese password para continuar");
                    }
                }else{
                    mostrarToast("Favor de ingresar nombre para continuar");
                }
            }
        });
        radioSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonMasculino){
                    sexo = "masculino";
                }
                if (checkedId == R.id.radioButtonFemenino){
                    sexo = "femenino";
                }
                if (checkedId == R.id.radioButtonDesconocido){
                    sexo = "desconocido";
                }
            }
        });
        radioRol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonAdmin){
                    rol = "administrador";
                }
                if (checkedId == R.id.radioButtonNormal){
                    rol = "usuario normal";
                }
            }
        });
    }
    ///se crea procedimiento para captura de usuarios al registro
    private void agregarUsuario(String nombre, String password, String correo, String sexo, String rol){
       usuario user=new usuario(0,nombre,password,sexo,rol,correo);
        agregarUsuarioABaseDeDatos(context,user);
        mostrarToast("Usuario agregado correctamente!!!");
        startActivity(new Intent(context,MainActivity.class));
    }
    private void configuracionesIniciales(){
        context = AgregarUsuarioActivity.this;
        edtNombre = findViewById(R.id.edtNombreUsuario);
        edtPassword = findViewById(R.id.edtPasswordUsuario);
        edtCorreo = findViewById(R.id.edtCorreoUsuario);
        radioSexo = findViewById(R.id.radioGroupSexo);
        radioRol = findViewById(R.id.radioGroupRoles);
        btnAgregar = findViewById(R.id.btnAgregarUsuario);
    }

    private void mostrarToast(String mensaje){
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }


}

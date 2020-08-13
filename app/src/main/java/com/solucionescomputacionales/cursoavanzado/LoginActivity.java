package com.solucionescomputacionales.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.solucionescomputacionales.cursoavanzado.variablesglobales.passwordTemporal;
import static com.solucionescomputacionales.cursoavanzado.variablesglobales.usuarioTemporal;

public class LoginActivity extends AppCompatActivity {
    TextView txtLoginVersion;
    EditText edtUsuario, edtPassword;
    Button  btnaccesar;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mando a llamar el metodo
        configuracionesiniciales();

    }
/// coloco mi boton fuera pero puede ser en el oncreater
    @Override
    protected void onResume() {
        super.onResume();
        btnaccesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario=edtUsuario.getText().toString();// se obtiene el usuario ingresado
                String password=edtPassword.getText().toString();//se obtiene password tecleado
                if (usuario.isEmpty()){//compara si el usuario esta vacio
                    Toast.makeText(context,"falta usuario",Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(context,"falta password",Toast.LENGTH_SHORT).show();

                }else { //entra aqui si el usuario y password no estan vacios
                    if (usuario.equals(usuarioTemporal)){
                        if (password.equals(passwordTemporal)){
                            Toast.makeText(context,"Acceso Correcto",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context,MainActivity.class));
                            myData mydata=new myData(context);//// dato para guardar usuario y no lo pida se puede omitir
                            mydata.guardarUsiario(usuario);//viene de mydata si no se quiere guardar se omite
                            mydata.setLogeo(true);//viene de mydata  si no se quiere guardar se omite

                        }else{
                            Toast.makeText(context,"usuario y/o contraseña incorrecto",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context,"usuario y/o contraseña incorrecto",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    /// se crea un metodo para las configuraciones iniciales
    private void configuracionesiniciales(){
        txtLoginVersion=findViewById(R.id.txtLoginVersion);
        edtUsuario=findViewById(R.id.edtUsuario);
        edtPassword=findViewById(R.id.edtPasswordUsuario);
        btnaccesar=findViewById(R.id.btnaccesar);
        context=LoginActivity.this;

    }
}
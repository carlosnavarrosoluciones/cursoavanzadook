package com.solucionescomputacionales.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import static com.solucionescomputacionales.cursoavanzado.variablesglobales.codigoQR;

public class QrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        scannerView=findViewById(R.id.zxingScanner);

        /// cual es el metodo con el que va a manejar
        scannerView.setResultHandler(QrActivity.this);
        ///inicializar camara
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Este metodo se ejecuta cuando se realiza el scan del QR
        codigoQR =result.getText();
        scannerView.stopCamera();
        startActivity(new Intent(QrActivity.this,MainActivity.class));
    }
}
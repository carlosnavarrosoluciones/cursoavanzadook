package com.solucionescomputacionales.cursoavanzado;

import android.provider.BaseColumns;

public class usuariosContrato {
    public static class usuariosColumnas implements BaseColumns{

        // SE DAN DE ALTA LOS CAMPOS DE LA TABLA
        public static final String NOMBRE="NOMBRE";
        public static final String PASSWORD="PASSWORD";
        public static final String GENERO="GENERO";
        public static final String ROL="ROL";
        public static final String CORREO="CORREO";

        ///SE DA DE ALTA EL NOMBRE DE LA TABLA
        public static final String TABLE_NAME="USUARIOS_TABLE";


    }
}

package com.solucionescomputacionales.cursoavanzado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends ArrayAdapter<usuario> {

    private Context context;
    private int LayoutID;
    private ArrayList<usuario> usuarios;

    public listAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<usuario> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.LayoutID=resource;
       usuarios=new ArrayList<>(objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if(convertView== null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(LayoutID, null);

            holder = new ViewHolder();
            holder.imgIcono = convertView.findViewById(R.id.imgListItemGenero);
            holder.txtNombre = convertView.findViewById(R.id.txtListItemNombre);
            holder.txtCorreo = convertView.findViewById(R.id.txtListItemCorreo);
            holder.txtID = convertView.findViewById(R.id.txtListItemID);
            holder.txtRol = convertView.findViewById(R.id.txtListItemRol);


        }else{
            holder=(ViewHolder)convertView.getTag();

        }
        usuario usuario=usuarios.get(position);
        if(usuario.getGenero().equals("masculino")) {
            holder.imgIcono.setImageResource(R.mipmap.male);
        }else if (usuario.getGenero().equals("femenino")){
            holder.imgIcono.setImageResource(R.mipmap.female);
        }else{
            holder.imgIcono.setImageResource(R.mipmap.indeterminado);
        }
        holder.txtNombre.setText(usuario.getNombre());
        holder.txtCorreo.setText(usuario.getCorreo());
        holder.txtID.setText(String.valueOf(usuario.getId()));
        holder.txtRol.setText(usuario.getRol());

        return convertView;

    }

    public class ViewHolder{
        ImageView imgIcono;
        TextView txtNombre,txtCorreo,txtID,txtRol;

    }
}

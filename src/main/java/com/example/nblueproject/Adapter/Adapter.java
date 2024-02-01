package com.example.nblueproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nblueproject.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    ArrayList<GetData> model;
    public Adapter(Context context,ArrayList<GetData> model){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.model = model;
    }
    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nama, jabatan, angkatan, nohp;
        View view1 = inflater.inflate(R.layout.list_staff, null);

        // Perhatikan perubahan di sini, dengan menempatkan return di luar blok if
        nama = view1.findViewById(R.id.namaanggota);
        jabatan = view1.findViewById(R.id.jabatananggota);
        angkatan = view1.findViewById(R.id.angkatananggota);
        nohp = view1.findViewById(R.id.nohpanggota);

        nama.setText(model.get(position).getNama());
        jabatan.setText(model.get(position).getJabatan());
        angkatan.setText(model.get(position).getAngkatan());
        nohp.setText(model.get(position).getTelp());

        return view1;
    }

}

package com.example.nblueproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nblueproject.R;

import java.util.ArrayList;

public class AdapterBarang extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    ArrayList<GetDataBarang> model1;

    public AdapterBarang(Context context, ArrayList<GetDataBarang> model1) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.model1 = model1;
    }

    @Override
    public int getCount() {
        return model1.size();
    }

    @Override
    public Object getItem(int position) {
        return model1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView idbarang, namaukm, namabarang, jumlahbarang;
        View view2 = inflater.inflate(R.layout.list_databarang, null);

        idbarang = view2.findViewById(R.id.idbarang);
        namaukm = view2.findViewById(R.id.namaukm);
        namabarang = view2.findViewById(R.id.namabarang);
        jumlahbarang = view2.findViewById(R.id.jumlahbarang);

        idbarang.setText(model1.get(position).getId());
        namaukm.setText(model1.get(position).getUkm());
        namabarang.setText(model1.get(position).getNama());
        jumlahbarang.setText(model1.get(position).getJml_barang());

        return view2;
    }
}

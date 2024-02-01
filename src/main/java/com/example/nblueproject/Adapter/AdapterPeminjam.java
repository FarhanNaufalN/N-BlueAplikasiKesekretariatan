package com.example.nblueproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nblueproject.R;

import java.util.ArrayList;

public class AdapterPeminjam extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    ArrayList<GetDataPeminjaman> model3;
    public AdapterPeminjam(Context context, ArrayList<GetDataPeminjaman> model3){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.model3 = model3;
    }
    @Override
    public int getCount() {
        return model3.size();
    }

    @Override
    public Object getItem(int position) {
        return model3.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView ID, Nama, ukmpeminjam, status, jumlahbarangg, namabarangpinjam, jumlahpinjam;
        View view3 = inflater.inflate(R.layout.list_peminjaman, null);

        GetDataPeminjaman dataPeminjaman = (GetDataPeminjaman) getItem(position);

        // Add jml_barang to the model
        String jml_barang = dataPeminjaman.getJumlahbarang();

        // Perhatikan perubahan di sini, dengan menempatkan return di luar blok if
        ID = view3.findViewById(R.id.idpeminjam);
        Nama = view3.findViewById(R.id.namapeminjam);
        ukmpeminjam = view3.findViewById(R.id.namaukmpeminjam);
        namabarangpinjam = view3.findViewById(R.id.namabarangpinjam);
        jumlahpinjam = view3.findViewById(R.id.jumlahbarangpinjam);
        status = view3.findViewById(R.id.status);



        ID.setText(model3.get(position).getId());
        Nama.setText(model3.get(position).getNama());
        ukmpeminjam.setText(model3.get(position).getUkm());
        namabarangpinjam.setText(model3.get(position).getNamabrg());
        jumlahpinjam.setText(model3.get(position).getJumlahpinjam());
        status.setText(model3.get(position).getStatus());


        return view3;
    }
}

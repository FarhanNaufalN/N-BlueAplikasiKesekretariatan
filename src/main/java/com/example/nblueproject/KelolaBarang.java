package com.example.nblueproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class KelolaBarang extends AppCompatActivity {
    EditText namabarangtambah, jumlahbarangtambah;
    Button tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_barang);

        namabarangtambah = findViewById(R.id.NamaBarangTambah);
        jumlahbarangtambah = findViewById(R.id.jumlahbarangtambah);

        tambah = findViewById(R.id.Tambahh);
        User user = User.getInstance();
        String ukm = user.getUkm();

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tambahbarang(ukm);
            }
        });
    }

    public void tambahbarang(String ukm){
        String namabarangg = namabarangtambah.getText().toString();
        String jumlahbarangg = jumlahbarangtambah.getText().toString();

        if (!(namabarangg.isEmpty() || jumlahbarangg.isEmpty())){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlTambahbarang, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected HashMap<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();

                    params.put("ukm", ukm);
                    params.put("nama", namabarangg);
                    params.put("jml_barang", jumlahbarangg);


                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(getApplicationContext(), "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show();
        }
    }
}
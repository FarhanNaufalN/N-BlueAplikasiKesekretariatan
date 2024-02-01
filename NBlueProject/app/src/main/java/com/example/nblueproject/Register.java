package com.example.nblueproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    private EditText etNim, etNama, etAngkatan, etTelp, etAlamat, etEmail, etPassword, etJabatan;
    private Button btnRegister;
    Spinner etJurusan, etUKM;

    private TextView tvLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNim = findViewById(R.id.nim);
        etNama = findViewById(R.id.nama);
        Spinner jurusanSpinner = findViewById(R.id.jurusan);
        Spinner ukmSpinner = findViewById(R.id.ukm);
        etAngkatan = findViewById(R.id.angkatan);
        etTelp = findViewById(R.id.nohp);
        etAlamat = findViewById(R.id.alamat);
        etJabatan = findViewById(R.id.jabatan);
        btnRegister = findViewById(R.id.Register);
        tvLogin = findViewById(R.id.loginbalik);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Jurusan, R.layout.etspinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jurusanSpinner.setAdapter(adapter1);
        jurusanSpinner.setOnItemSelectedListener(null);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.ukm, R.layout.etspinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ukmSpinner.setAdapter(adapter2);
        ukmSpinner.setOnItemSelectedListener(null);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Nim = etNim.getText().toString();
                String Nama = etNama.getText().toString();
                String Jurusan = jurusanSpinner.getSelectedItem().toString();
                String Angkatan = etAngkatan.getText().toString();
                String UKM = ukmSpinner.getSelectedItem().toString();
                String Telp = etTelp.getText().toString();
                String Alamat = etAlamat.getText().toString();
                String Jabatan = etJabatan.getText().toString();

                if (!(Nim.isEmpty() || Nama.isEmpty() || Jurusan.isEmpty() || Angkatan.isEmpty() || UKM.isEmpty() || Telp.isEmpty() || Alamat.isEmpty() || Jabatan.isEmpty())){

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlRegister, new Response.Listener<String>() {
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
                        protected HashMap<String, String> getParams() throws AuthFailureError{
                            HashMap<String, String> params = new HashMap<>();

                            params.put("nim", Nim);
                            params.put("nama", Nama);
                            params.put("jurusan", Jurusan);
                            params.put("angkatan", Angkatan);
                            params.put("ukm", UKM);
                            params.put("telp", Telp);
                            params.put("alamat", Alamat);
                            params.put("jabatan", Jabatan);

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }else{
                    Toast.makeText(getApplicationContext(), "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
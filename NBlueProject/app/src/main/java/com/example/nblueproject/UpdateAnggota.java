package com.example.nblueproject;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateAnggota extends AppCompatActivity {

    private EditText et1Nim, et1Nama, et1Angkatan, et1Telp, et1Alamat, et1Email, et1Password, et1Jabatan;
    private Button btnRegister;
    Spinner jurusanSpinner, ukmSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_anggota);

        et1Nim = findViewById(R.id.nim1);
        et1Nama = findViewById(R.id.nama1);
        jurusanSpinner = findViewById(R.id.jurusan1);
        ukmSpinner = findViewById(R.id.ukm1);
        et1Angkatan = findViewById(R.id.angkatan1);
        et1Telp = findViewById(R.id.nohp1);
        et1Alamat = findViewById(R.id.alamat1);
        et1Jabatan = findViewById(R.id.jabatan1);
        btnRegister = findViewById(R.id.Register1);
        User user = User.getInstance();
        String userNim = user.getNim();
        getData(userNim);

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

        // Button untuk menampilkan data anggota
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nim = et1Nim.getText().toString();
                String Nama = et1Nama.getText().toString();
                String Jurusan = jurusanSpinner.getSelectedItem().toString();
                String Angkatan = et1Angkatan.getText().toString();
                String UKM = ukmSpinner.getSelectedItem().toString();
                String Telp = et1Telp.getText().toString();
                String Alamat = et1Alamat.getText().toString();
                String Jabatan = et1Jabatan.getText().toString();

                if (!(Nim.isEmpty() || Nama.isEmpty() || Jurusan.isEmpty() || Angkatan.isEmpty() || UKM.isEmpty() || Telp.isEmpty() || Alamat.isEmpty() || Jabatan.isEmpty())) {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlUpdateAnggota, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Handle response from the server after a successful update
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error response, if any
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected HashMap<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<>();

                            // Include parameters needed for the update operation
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

                } else {
                    Toast.makeText(getApplicationContext(), "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getData(String userNim) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlgetdata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject dataObject = jsonObject.getJSONObject("data");

                    String gnim = dataObject.getString("nim");
                    String gnama = dataObject.getString("nama");
                    String gjurusan = dataObject.getString("jurusan");
                    String gangkatan = dataObject.getString("angkatan");
                    String gukm = dataObject.getString("ukm");
                    String gtelp = dataObject.getString("telp");
                    String galamat = dataObject.getString("alamat");
                    String gjabatan = dataObject.getString("jabatan");

                    et1Nim.setText(gnim);
                    et1Nama.setText(gnama);

                    // Set nilai jurusanSpinner
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(UpdateAnggota.this,
                            R.array.Jurusan, R.layout.etspinner);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    jurusanSpinner.setAdapter(adapter1);

                    // Set selected value for jurusanSpinner
                    int jurusanPosition = adapter1.getPosition(gjurusan);
                    jurusanSpinner.setSelection(jurusanPosition);

                    et1Angkatan.setText(gangkatan);

                    // Set nilai ukmSpinner
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(UpdateAnggota.this,
                            R.array.ukm, R.layout.etspinner);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ukmSpinner.setAdapter(adapter2);

                    // Set selected value for ukmSpinner
                    int ukmPosition = adapter2.getPosition(gukm);
                    ukmSpinner.setSelection(ukmPosition);

                    et1Telp.setText(gtelp);
                    et1Alamat.setText(galamat);
                    et1Jabatan.setText(gjabatan);

                } catch (JSONException e) {
                    e.printStackTrace(); // Log the exception or handle it as appropriate
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error response, if any
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("nim", getIntent().getStringExtra("edit_data"));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}



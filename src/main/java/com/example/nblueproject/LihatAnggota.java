package com.example.nblueproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class LihatAnggota extends AppCompatActivity {
    private TextView tv1Nim, tv1Nama, tv1Angkatan, tv1Telp, tv1Alamat, tv1Email, tv1Password, tv1Jabatan, tv1jurusan, tv1ukm, tv1nohp;
    private Button bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_anggota);


        tv1Nim = findViewById(R.id.lnim);
        tv1Nama = findViewById(R.id.lnama);
        tv1jurusan = findViewById(R.id.ljurusan);
        tv1Angkatan = findViewById(R.id.langkatan);
        tv1ukm = findViewById(R.id.lukm);
        tv1nohp = findViewById(R.id.lnohp);
        tv1Alamat = findViewById(R.id.lalamat);
        tv1Jabatan = findViewById(R.id.ljabatan);
        bt3 = findViewById(R.id.bt3);

        User user = User.getInstance();
        String Nim = user.getNim();
        getData(Nim);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatAnggota.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    void getData(String Nim) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlgetdata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject dataObject = jsonObject.getJSONObject("data");

                    String lnim = dataObject.getString("nim");
                    String lnama = dataObject.getString("nama");
                    String ljurusan = dataObject.getString("jurusan");
                    String langkatan = dataObject.getString("angkatan");
                    String lukm = dataObject.getString("ukm");
                    String ltelp = dataObject.getString("telp");
                    String lalamat = dataObject.getString("alamat");
                    String ljabatan = dataObject.getString("jabatan");

                    // Set teks pada TextView
                    tv1Nim.setText(lnim);
                    tv1Nama.setText(lnama);
                    tv1jurusan.setText(ljurusan);
                    tv1Angkatan.setText(langkatan);
                    tv1ukm.setText(lukm);
                    tv1nohp.setText(ltelp);
                    tv1Alamat.setText(lalamat);
                    tv1Jabatan.setText(ljabatan);

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

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
import com.example.nblueproject.Adapter.AdapterBarang;
import com.example.nblueproject.Adapter.GetDataBarang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditBarang extends AppCompatActivity {

    private EditText EidBarang, Enamabarang,Ejumlahbarang ;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        EidBarang = findViewById(R.id.EidBarang);
        Enamabarang = findViewById(R.id.ENamabarang);
        Ejumlahbarang = findViewById(R.id.Ejumlahbarang);
        btnRegister = findViewById(R.id.Register1);
        User user = User.getInstance();
        String userNim = user.getNim();
        final String idbarang = user.getIdbarang();
        getDataBarang(idbarang);



        // Button untuk menampilkan data anggota
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idbarang = EidBarang.getText().toString();
                String Namabarang = Enamabarang.getText().toString();
                String Jumlahbarang = Ejumlahbarang.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlUpdateBarang, new Response.Listener<String>() {
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
                        params.put("id", idbarang);
                        params.put("nama", Namabarang);
                        params.put("jml_barang", Jumlahbarang);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }
    private void getDataBarang(String idbarang) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlGetstockbarang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject dataObject = jsonObject.getJSONObject("data");

                    String bid = dataObject.getString("id");
                    String bnama = dataObject.getString("nama");
                    String bjumlah = dataObject.getString("jml_barang");
                    String bukm = dataObject.getString("ukm");

                    EidBarang.setText(bid);
                    Enamabarang.setText(bnama);
                    Ejumlahbarang.setText(bjumlah);


                    // Set teks pada TextView


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
                params.put("id", getIntent().getStringExtra("edit_data"));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}



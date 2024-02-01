package com.example.nblueproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pinjambarang extends AppCompatActivity {

    TextView idpeminjaman, stokbarang, tv2ukm, namabarang, idbarangg,ukmbaranggg;

    EditText jumlahbarang;
    Button pinjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjambarang);

        idpeminjaman = findViewById(R.id.idbarang);
        stokbarang = findViewById(R.id.stockbarang);
        tv2ukm = findViewById(R.id.ukmpinjam);
        namabarang = findViewById(R.id.namabarang);
        idbarangg = findViewById(R.id.idbarangg);
        ukmbaranggg= findViewById(R.id.ukmbarangg);
        jumlahbarang = findViewById(R.id.edtJumlahBarang);
        pinjam = findViewById(R.id.pinjam);

        // Mengambil data dari Intent
        User user = User.getInstance();
        final String idPeminjam = user.getNim();
        final String ukm = user.getUkm();
        final String idbarang = user.getIdbarang();
        final String jml_barang = user.getStockbarang();

        // Menetapkan nilai pada TextView
        tv2ukm.setText(ukm);


        // Mendapatkan ID peminjaman terbaru dari server
        getLatestPeminjamanID();
        getDataBarang(idbarang);

        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Melanjutkan dengan proses pengajuan peminjaman
                pinjambarang(idPeminjam, namabarang.getText().toString());
                detailpinjam(idpeminjaman.getText().toString(), ukmbaranggg.getText().toString(), jumlahbarang.getText().toString());
                tambahnotif(namabarang.getText().toString());
            }
        });
    }

    private void getLatestPeminjamanID() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlGetdatapinjam, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    int latestID = Integer.parseInt(response);
                    idpeminjaman.setText(String.valueOf(latestID + 1));
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Gagal mendapatkan ID terbaru", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
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



                    namabarang.setText(bukm);
                    stokbarang.setText(bjumlah);
                    idbarangg.setText(bnama);
                    ukmbaranggg.setText(bid);
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

    private void pinjambarang(String idPeminjam,String ukm) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlPeminjaman, new Response.Listener<String>() {
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
                params.put("id_peminjam", idPeminjam);
                params.put("ukm", ukm);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void detailpinjam(String idpeminjaman,String idbarang,String jml_barang) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlInputDetail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Handle response from the server after a successful update
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                User user = User.getInstance();
                user.setJumlahpinjamm(String.valueOf(jumlahbarang));
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
                params.put("id_peminjaman", idpeminjaman);
                params.put("id_barang", idbarang);
                params.put("jml_barang", jml_barang);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void ambilnotif(String ukm) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlAmbilnomor,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");

                            // Check if the response contains success message
                            if (message.equals("Sukses")) {
                                // Ambil detail peminjaman setelah mendapatkan respons
                                if (jsonObject.has("nomor")) {

                                    String nomor = jsonObject.getString("nomor");

                                    // Simpan data di kelas DetailPinjam
                                    Notif notif = Notif.getInstance();
                                    notif.setNomor(nomor);

                                    // Tampilkan notifikasi sukses ambil nomor
                                    showSuccessNotification();

                                    // Panggil fungsi kembalikanbarang dengan parameter yang diperlukan

                                } else {
                                    // Handle the case when the response is missing expected keys
                                    Toast.makeText(getApplicationContext(), "Data tidak lengkap", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Handle the case when the response indicates failure
                                Toast.makeText(getApplicationContext(), "Gagal mengambil data: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle the case when the response is missing the "message" key
                            Toast.makeText(getApplicationContext(), "Format respons tidak valid", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ukm", ukm);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showSuccessNotification() {
        Toast.makeText(getApplicationContext(), "Nomor berhasil diambil", Toast.LENGTH_SHORT).show();
    }


    private void tambahnotif(String ukm){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlNotif, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Handle response from the server after a successful update
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                ambilnotif(ukm);
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
                params.put("ukm", ukm);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}

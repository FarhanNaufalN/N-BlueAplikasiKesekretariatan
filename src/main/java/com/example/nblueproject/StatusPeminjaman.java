package com.example.nblueproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nblueproject.Adapter.AdapterPeminjam;
import com.example.nblueproject.Adapter.GetDataPeminjaman;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatusPeminjaman extends AppCompatActivity {

    private ListView viewPeminjaman;
    private AdapterPeminjam adapterpeminjam;
    private ArrayList<GetDataPeminjaman> model3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_peminjaman);

        // Inisialisasi elemen UI
        viewPeminjaman = findViewById(R.id.Liststatus);
        if (viewPeminjaman != null) {
            User user = User.getInstance();
            String userUkm = user.getUkm();
            String stok = user.getStockbarang();
            DetailPinjam detailPinjam = DetailPinjam.getInstance();
            String idpeminjam = detailPinjam.getIdpeminjamanbrg();
            String idbrg = detailPinjam.getIdbrg();
            String Jml_barang = detailPinjam.getJml_barang();
            load_data(userUkm);

            viewPeminjaman.setOnItemClickListener((parent, view, position, id) -> {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_status, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(item -> {
                    int i = position;
                    String idPeminjaman = model3.get(i).getId();

                    if (item.getItemId() == R.id.kembalikan) {
                        // Panggil fungsi kembalikanbarang dengan parameter yang diperlukan
                        ambildatadetail(idPeminjaman);
                        statuskembalikan(idPeminjaman,userUkm);
                    }
                    return true;
                });
            });
        }
    }

    private void load_data(String ukm) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlListStatus,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.has("data")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            model3 = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dataObject = jsonArray.getJSONObject(i);

                                // Check if the UKM matches the selected UKM
                                model3.add(new GetDataPeminjaman(
                                        dataObject.getString("id"),
                                        dataObject.getString("nama"),
                                        dataObject.getString("ukm"),
                                        dataObject.getString("nama_barang"),
                                        dataObject.getString("jml_barang"),
                                        dataObject.getString("status")
                                ));
                            }

                            adapterpeminjam = new AdapterPeminjam(getApplicationContext(), model3);
                            viewPeminjaman.setAdapter(adapterpeminjam);

                        } else {
                            Toast.makeText(getApplicationContext(), "Tidak ada data untuk UKM ini", Toast.LENGTH_SHORT).show();
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

    private void statuskembalikan(String idPeminjaman, String ukm){
        User user = User.getInstance();
        String userUkm = user.getUkm();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlStatuskembali,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        String status = jsonObject.getString("status");
                        String message = jsonObject.getString("message");

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        // Refresh tampilan setelah melakukan Acc
                        load_data(userUkm);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", idPeminjaman);
                params.put("ukm", ukm);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void ambildatadetail(String idPeminjaman) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlAmbildetail,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");

                            // Check if the response contains success message
                            if (message.equals("Sukses")) {
                                // Ambil detail peminjaman setelah mendapatkan respons
                                if (jsonObject.has("id") && jsonObject.has("id_peminjaman") &&
                                        jsonObject.has("id_barang") && jsonObject.has("jml_barang")) {

                                    String iddetail = jsonObject.getString("id");
                                    String idpeminjamanbrg = jsonObject.getString("id_peminjaman");
                                    String idbrg = jsonObject.getString("id_barang");
                                    String Jml_barang = jsonObject.getString("jml_barang");

                                    // Simpan data di kelas DetailPinjam
                                    DetailPinjam detailPinjam = DetailPinjam.getInstance();
                                    detailPinjam.setIddetail(iddetail);
                                    detailPinjam.setIdpeminjamanbrg(idpeminjamanbrg);
                                    detailPinjam.setIdbrg(idbrg);
                                    detailPinjam.setJml_barang(Jml_barang);

                                    // Panggil fungsi kembalikanbarang dengan parameter yang diperlukan
                                    kembalikanbarang(idbrg, Jml_barang);

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
                params.put("id", idPeminjaman);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void kembalikanbarang(String idBrg, String jumlahBarang) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlKembalikan,
                response -> {
                    Log.d("KembalikanBarang", "Respon: " + response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String message = jsonObject.getString("message");

                        // Tampilkan pesan respons
                        Toast.makeText(getApplicationContext(), "Berhasil Kembalikan Barang", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Gagal Kembalikan", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", idBrg);
                params.put("jml_barang", jumlahBarang);
                return params;
            }
        };

        // Tambahkan request ke queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}

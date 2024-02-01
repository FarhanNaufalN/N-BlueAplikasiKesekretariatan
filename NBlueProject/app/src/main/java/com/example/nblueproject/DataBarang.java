package com.example.nblueproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

public class DataBarang extends AppCompatActivity {

    private ListView listView;

    private Button tambahbarang;
    private AdapterBarang adapterbarang;
    private ArrayList<GetDataBarang> model1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_barang);

        listView = findViewById(R.id.Listbarang);
        tambahbarang = findViewById(R.id.TambahBarang);


        User user = User.getInstance();
        final String idPeminjam = user.getNim();
        final String ukm = user.getUkm();
        final String idbarang = user.getIdbarang();
        final String jml_barang = user.getStockbarang();

        // Load data based on the selected UKM
        load_data(ukm);


        listView.setOnItemClickListener((parent, view, position, id) -> {
            PopupMenu popupMenu = new PopupMenu(DataBarang.this, view);
            popupMenu.getMenuInflater().inflate(R.menu.menu_kelolabarang, popupMenu.getMenu());
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(item -> {
                int i = position;

                if (item.getItemId() == R.id.EditBarang) {
                    Intent intent = new Intent(getApplicationContext(), EditBarang.class);
                    intent.putExtra("edit_data", model1.get(i).getId());
                    startActivity(intent);
                } else if (item.getItemId() == R.id.HapusBarang){
                    Hapus(model1.get(i).getId());
                }
                return true;
            });

            tambahbarang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), KelolaBarang.class);
                    startActivity(intent);
                }
            });


        });
    }

    private void load_data(String ukm) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlDatabarang,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.has("data")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            model1 = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dataObject = jsonArray.getJSONObject(i);

                                // Check if the UKM matches the selected UKM
                                if (dataObject.getString("ukm").equals(ukm)) {
                                    model1.add(new GetDataBarang(
                                            dataObject.getString("id"),
                                            dataObject.getString("nama"),
                                            dataObject.getString("jml_barang"),
                                            dataObject.getString("ukm")
                                    ));
                                }
                            }

                            adapterbarang = new AdapterBarang(getApplicationContext(), model1);
                            listView.setAdapter(adapterbarang);
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

    private void Hapus(String idbarang) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlHapusBarang,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if (status.equals("success")) {
                            Toast.makeText(getApplicationContext(), "Data telah dihapus", Toast.LENGTH_SHORT).show();
                            load_data(User.getInstance().getUkm());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", idbarang);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}

package com.example.nblueproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class Peminjaman extends AppCompatActivity {

    private ListView listView;
    private AdapterBarang adapterbarang;
    private ArrayList<GetDataBarang> model1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

        listView = findViewById(R.id.Listbarang);
        Spinner spinner = findViewById(R.id.ukmbarang);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ukm, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected UKM from the spinner
                String selectedUkm = parentView.getItemAtPosition(position).toString();

                // Load data based on the selected UKM
                load_data(selectedUkm);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        // Load data initially with the default UKM (assuming you have a default UKM)
        load_data(spinner.getSelectedItem().toString());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            PopupMenu popupMenu = new PopupMenu(Peminjaman.this, view);
            popupMenu.getMenuInflater().inflate(R.menu.menu_pinjam, popupMenu.getMenu());
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(item -> {
                int i = position;

                if (item.getItemId() == R.id.Pinjam) {
                    Intent intent = new Intent(getApplicationContext(), Pinjambarang.class);
                    intent.putExtra("edit_data", model1.get(i).getId());
                    startActivity(intent);
                }
                return true;
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
}

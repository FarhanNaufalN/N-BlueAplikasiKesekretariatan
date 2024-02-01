package com.example.nblueproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nblueproject.Adapter.Adapter;
import com.example.nblueproject.Adapter.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StaffFragment extends Fragment {

    private ListView listView;
    private Adapter adapter;
    private ArrayList<GetData> model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff, container, false);

        listView = view.findViewById(R.id.list);

        if (listView != null) {
            User user = User.getInstance();
            String userUkm = user.getUkm();
            load_data(userUkm);

            listView.setOnItemClickListener((parent, view1, position, id) -> {
                PopupMenu popupMenu = new PopupMenu(requireContext(), view1);
                popupMenu.getMenuInflater().inflate(R.menu.menu_opsi, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(item -> {
                    int i = position;

                    if (item.getItemId() == R.id.Lihat) {
                        Intent intent = new Intent(requireContext(), LihatAnggota.class);
                        intent.putExtra("edit_data", model.get(i).getNim());
                        startActivity(intent);
                    } else if (item.getItemId() == R.id.Edit) {
                        Intent intent = new Intent(requireContext(), UpdateAnggota.class);
                        intent.putExtra("edit_data", model.get(i).getNim());
                        startActivity(intent);
                    } else if (item.getItemId() == R.id.Hapus) {
                        Hapus(model.get(i).getNim());
                    }
                    return false;
                });
            });
        }

        return view;
    }

    private void Hapus(String userNim) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlHapusData,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if (status.equals("success")) {
                            Toast.makeText(requireContext(), "Data telah dihapus", Toast.LENGTH_SHORT).show();
                            load_data(User.getInstance().getUkm());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("nim", userNim);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }

    private void load_data(String ukm) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlTampilAnggota,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.has("data")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            model = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dataObject = jsonArray.getJSONObject(i);

                                model.add(new GetData(
                                        dataObject.getString("nim"),
                                        dataObject.getString("nama"),
                                        dataObject.getString("jabatan"),
                                        dataObject.getString("angkatan"),
                                        dataObject.getString("telp")
                                ));
                            }

                            adapter = new Adapter(requireContext(), model);
                            listView.setAdapter(adapter);
                        } else {
                            Toast.makeText(requireContext(), "Tidak ada data untuk UKM ini", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(requireContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(requireContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ukm", ukm);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }
}

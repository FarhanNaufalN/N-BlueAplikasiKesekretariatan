package com.example.nblueproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class HomeFragment extends Fragment {
    private View fragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);


        User user = User.getInstance();
        String userUkm = user.getUkm();
        ambilnotif(userUkm);

        Notif notiff = Notif.getInstance();// Atur nilai nomor sesuai kebutuhan
        final String notifff = notiff.getNomor();

        TextView notif = fragmentView.findViewById(R.id.nomorr);
        notif.setText(String.valueOf(notifff));


        // Temukan CardView
        CardView peminjamanCardView = fragmentView.findViewById(R.id.peminjaman);
        CardView pengembalianCardView = fragmentView.findViewById(R.id.pengembalian);
        CardView kelolaCardView = fragmentView.findViewById(R.id.kelolabarang);
        CardView TambahstaffCardView = fragmentView.findViewById(R.id.TambahAnggota);

        // Tambahkan listener klik pada setiap CardView
        peminjamanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasikan navigasi ke halaman baru
                // Misalnya:
                Intent intent = new Intent(getActivity(), Peminjaman.class);
                startActivity(intent);
            }
        });

        pengembalianCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasikan navigasi ke halaman baru
                // Misalnya:
                Intent intent = new Intent(getActivity(), AccPeminjaman.class);
                startActivity(intent);
                hapusnotif(userUkm);
                ambilnotif(userUkm);
            }
        });

        kelolaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasikan navigasi ke halaman baru
                // Misalnya:
                Intent intent = new Intent(getActivity(), DataBarang.class);
                startActivity(intent);
            }
        });

        TambahstaffCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasikan navigasi ke halaman baru
                // Misalnya:
                Intent intent = new Intent(getActivity(), Register.class);
                startActivity(intent);
            }
        });

        // Tambahkan listener klik untuk CardView lainnya sesuai kebutuhan

        return fragmentView;
    }

    private void hapusnotif(String ukm){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlHapusnotif, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Handle response from the server after a successful update
                Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error response, if any
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }
    private void ambilnotif(String ukm) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlAmbilnomor,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");

                            // Check if the response contains a success message
                            if (message.equals("Sukses")) {
                                // Ambil detail peminjaman setelah mendapatkan respons
                                if (jsonObject.has("nomor")) {

                                    String nomor = jsonObject.getString("nomor");

                                    // Simpan data di kelas DetailPinjam

                                    TextView notif = fragmentView.findViewById(R.id.nomorr);
                                    notif.setText(String.valueOf(nomor));

                                    // Panggil fungsi kembalikanbarang dengan parameter yang diperlukan

                                } else {
                                    // Handle the case when the response is missing expected keys
                                    Toast.makeText(requireContext(), "Data tidak lengkap", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Handle the case when the response indicates failure
                                Toast.makeText(requireContext(), "Gagal mengambil data: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle the case when the response is missing the "message" key
                            Toast.makeText(requireContext(), "Format respons tidak valid", Toast.LENGTH_SHORT).show();
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
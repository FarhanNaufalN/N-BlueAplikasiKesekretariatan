package com.example.nblueproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Login extends AppCompatActivity {

    private EditText etEmailLogin, etPasswordLogin;
    private Button btnLogin;
    private TextView tvRegis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLogin = findViewById(R.id.emailLogin);
        etPasswordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.loginbtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmailLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlLogin, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                if (jsonObject.has("message")) {
                                    String message = jsonObject.getString("message");

                                    if (message.equals("Selamat Datang")) {
                                        // Setelah mendapatkan respons dari server
                                        Toast.makeText(getApplicationContext(), "Selamat Datang", Toast.LENGTH_SHORT).show();

                                        // Pastikan respons mengandung UKM dan Nama
                                        if (jsonObject.has("ukm") && jsonObject.has("nama") && jsonObject.has("nim")) {
                                            // Ambil nilai UKM dan Nama dari respons
                                            String ukm = jsonObject.getString("ukm");
                                            String nama = jsonObject.getString("nama");
                                            String nim = jsonObject.getString("nim");

                                            // Set nilai UKM dan Nama di kelas User
                                            User user = User.getInstance();
                                            user.setUkm(ukm);
                                            user.setNama(nama);
                                            user.setNim(nim);

                                            // Implementasi: Beralih ke halaman utama aplikasi
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        } else {
                                            // Menampilkan pesan jika tidak ada UKM atau Nama dalam respons
                                            Toast.makeText(getApplicationContext(), "Tidak ada data UKM atau Nama dalam respons", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Menampilkan pesan sesuai dengan respons PHP
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Menampilkan pesan jika tidak ada "message" dalam respons
                                    Toast.makeText(getApplicationContext(), "Respon tidak valid", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            // Mengatur parameter POST untuk mengirimkan email dan password ke server
                            Map<String, String> params = new HashMap<>();
                            params.put("email", email);
                            params.put("password", password);
                            return params;
                        }
                    };

                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "Password Atau Username Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

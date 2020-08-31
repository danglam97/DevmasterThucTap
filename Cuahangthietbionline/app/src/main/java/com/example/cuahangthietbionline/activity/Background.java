package com.example.cuahangthietbionline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class Background extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        Dangnhap.sharedPreferences  = getSharedPreferences("datalogin",MODE_PRIVATE);
        Dangnhap.tendn =Dangnhap.sharedPreferences.getString("taikhoan","");
        Dangnhap.mkdn =Dangnhap.sharedPreferences.getString("matkhau","");
       if (Dangnhap.tendn.length() > 0 && Dangnhap.mkdn.length() > 0){
           Lutru();

       }else {
           Intent intent = new Intent(getApplicationContext(),Dangnhap.class);
           startActivity(intent);
       }


    }
    private void Lutru() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandangnhap, new Response.Listener<String>() {
            @Override
            public void onResponse(String luutru) {
                if(luutru.equals("sai")){

                }
                else {
                    Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    Log.d("AAA",luutru);
                    Dangnhap.idtk = luutru;



                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String,String>();
                hashMap.put("tendn",Dangnhap.tendn);
                hashMap.put("mkdn",Dangnhap.mkdn);

                return hashMap;}
        };
        requestQueue.add(stringRequest);

    }

}
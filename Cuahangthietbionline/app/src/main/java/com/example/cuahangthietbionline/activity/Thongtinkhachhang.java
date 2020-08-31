package com.example.cuahangthietbionline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.ultil.CheckConnetion;
import com.example.cuahangthietbionline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Thongtinkhachhang extends AppCompatActivity {
    EditText edttenkhachhang, edtemail, edtsdt;
    Button btnxacnhan, btntrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
        Anhxa();
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (CheckConnetion.haveNetworkConnection(getApplicationContext())) {

            EventButton();

        } else {
            CheckConnetion.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");
        }
    }

    private void EventButton() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten = edttenkhachhang.getText().toString().trim();
                final String sdt = edtsdt.getText().toString().trim();
                final String email = edtemail.getText().toString().trim();
                if(ten.length()>0 && sdt.length() >0 && email.length() >0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                           if(Integer.parseInt(madonhang) > 0){
                               RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                               StringRequest request =new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                   @Override
                                   public void onResponse(String response) {
                                       if(response.equals("1")){
                                           MainActivity.manggiohang.clear();

                                           Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                           startActivity(intent);
                                           CheckConnetion.ShowToast_Short(getApplicationContext(),"Thông tin đơn hàng đã được xác nhận! Mời bạn tiếp tục mua hàng");
                                       }else{
                                           CheckConnetion.ShowToast_Short(getApplicationContext(),"Dữ liệu giỏ hàng bị lỗi! vui lòng thao tac lại");

                                       }

                                   }
                               }, new Response.ErrorListener() {
                                   @Override
                                   public void onErrorResponse(VolleyError error) {

                                   }
                               }){
                                   @Override
                                   protected Map<String, String> getParams() throws AuthFailureError {
                                       JSONArray jsonArray = new JSONArray();
                                       for (int i = 0;i < MainActivity.manggiohang.size();i++){
                                           JSONObject jsonObject =new JSONObject();
                                           try {
                                               jsonObject.put("madonhang",madonhang);
                                               jsonObject.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
                                               jsonObject.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                               jsonObject.put("giasanpham",MainActivity.manggiohang.get(i).getGiasp());
                                               jsonObject.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());
                                               jsonObject.put("idtaikhoan",Dangnhap.idtk);


                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                           jsonArray.put(jsonObject);
                                       }
                                       HashMap<String,String> hashMap = new HashMap<String, String>();
                                       hashMap.put("json",jsonArray.toString());
                                       return hashMap;
                                   }
                               };
                               queue.add(request);
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
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    CheckConnetion.ShowToast_Short(getApplicationContext(),"Vui lòng nhập đủ các thông tin");
                }
            }
        });
    }



    private void Anhxa() {
        edttenkhachhang = findViewById(R.id.edittexttenkhachhang);
        edtsdt = findViewById(R.id.edittextsodienthoai);
        edtemail = findViewById(R.id.edittextemail);
        btnxacnhan = findViewById(R.id.buttonxacnhan);
        btntrove = findViewById(R.id.buttontrove);

    }
}
package com.example.cuahangthietbionline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class Dangnhap extends AppCompatActivity {
    Button btndangnhap,btndangky;
    EditText edtname,edtpass;
   String ten,mk,sdt;
   public static String tendn,mkdn;
        public static String  idtk = "";



  public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Anhxa();




        Controlbutton();
    }


    private void Controlbutton() {
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tendn = edtname.getText().toString().trim();
                mkdn = edtpass.getText().toString().trim();
                if(tendn.length()>0 && mkdn.length() > 0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandangnhap, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String dangnhap) {
                            if(dangnhap.equals("sai")){
                                CheckConnetion.ShowToast_Short(getApplicationContext(),"Tài khoản hoặc mật khẩu ko chính xác");

                            }else{
                                CheckConnetion.ShowToast_Short(getApplicationContext(),"Đăng nhập thành công");
                                Log.d("AAA",dangnhap);
                                idtk = dangnhap;


                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("taikhoan",tendn);
                                editor.putString("matkhau",mkdn);
                                editor.commit();

                                Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            CheckConnetion.ShowToast_Short(getApplicationContext(),"kiểm tra kết nối Internet");

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("tendn",tendn);
                            hashMap.put("mkdn",mkdn);

                            return hashMap;}
                    };
                    requestQueue.add(stringRequest);

                }
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Dangnhap.this);
                dialog.setTitle("Đăng ký");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dangky);
                dialog.show();
                final EditText edttendangky = dialog.findViewById(R.id.edittexttendangky);
                final EditText edtpasdangky = dialog.findViewById(R.id.edittextpassdangky);
                final EditText edtsdtdangky = dialog.findViewById(R.id.edittextsdtdangky);
                Button btndangky =dialog.findViewById(R.id.btndangky);
                Button btnhuy = dialog.findViewById(R.id.btnhuy);
                btndangky.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ten =edttendangky.getText().toString().trim();
                        mk = edtpasdangky.getText().toString().trim();
                        sdt = edtsdtdangky.getText().toString().trim();
                        if(ten.length()>0 && sdt.length() >0 && sdt.length() >0){
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandangky, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    if (response.equals("trung")){
                                        CheckConnetion.ShowToast_Short(getApplicationContext(),"Tên tài khoản đã tồn tại");
                                    }else{
                                        CheckConnetion.ShowToast_Short(getApplicationContext(),"Đăng ký thành công");
                                        edtname.setText(ten);
                                        edtpass.setText(mk);
                                        dialog.cancel();
                                    }




                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    CheckConnetion.ShowToast_Short(getApplicationContext(),"Đăng ký thất bại");

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String,String> hashMap = new HashMap<String,String>();
                                    hashMap.put("ten",ten);
                                    hashMap.put("mk",mk);
                                    hashMap.put("sdt",sdt);
                                    return hashMap;}
                            };
                            requestQueue.add(stringRequest);

                        }else{
                            CheckConnetion.ShowToast_Short(getApplicationContext(),"mời bạn nhập đủ thông tin ");
                        }


                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });



            }
        });
    }

    private void Anhxa() {
        btndangnhap = findViewById(R.id.bt);
        btndangky = findViewById(R.id.bt2);
        edtname = findViewById(R.id.edit);
        edtpass =findViewById(R.id.edi2);
    }
}
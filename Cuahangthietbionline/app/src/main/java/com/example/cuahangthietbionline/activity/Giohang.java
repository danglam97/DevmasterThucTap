package com.example.cuahangthietbionline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.adapter.GiohangAdapter;
import com.example.cuahangthietbionline.ultil.CheckConnetion;

import java.text.DecimalFormat;

public class Giohang extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan,btntieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUltil();
        CactchOnItemListview();
        EventButton();
    }

    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() >0 ){
                    Intent intent = new Intent(getApplicationContext(),Thongtinkhachhang.class);
                    startActivity(intent);

                }else {
                    CheckConnetion.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm");
                }
            }
        });
    }

    private void CactchOnItemListview() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.manggiohang.size() <= 0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if(MainActivity.manggiohang.size() <= 0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();

                            }

                        }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EvenUltil() {
        long tongtien = 0;
        for (int i = 0 ; i<MainActivity.manggiohang.size(); i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) + " Đ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size() <= 0){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang =findViewById(R.id.listviewgiohang);
        txtthongbao =findViewById(R.id.textviewthongbao);
        txttongtien = findViewById(R.id.textviewtongtien);
        btnthanhtoan = findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmua = findViewById(R.id.buttontieptucmuahang);
        toolbargiohang = findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(Giohang.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
}
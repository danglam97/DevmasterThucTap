package com.example.cuahangthietbionline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.model.Giohang;
import com.example.cuahangthietbionline.model.Sanpham;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
Toolbar toolbarchitiet;
ImageView imgchitiet;
TextView txtten,txtgia,txtmota;
Spinner spinner;
Button btndatmua;
    int id = 0;
    String TenChitiet = "";
    int GiaChitiet = 0 ;
    String HinhanhChitiet = "";
    String MotaChitiet = "";
    int Idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInfrommation();
        CatchEventSpinner();
        EventButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.cuahangthietbionline.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size()>0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0 ; i<MainActivity.manggiohang.size();i++){
                        if(MainActivity.manggiohang.get(i).getIdsp() == id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp()>=10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi =soluong * GiaChitiet;
                        MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                    }
                }else {
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi =soluong * GiaChitiet;
                    MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));

                }
                Intent intent = new Intent(getApplicationContext(), com.example.cuahangthietbionline.activity.Giohang.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayadapter);
    }

    private void GetInfrommation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id =sanpham.getID();
        TenChitiet = sanpham.getTensanpham();
        GiaChitiet =sanpham.getGiasanpham();
        HinhanhChitiet = sanpham.getHinhanhsanpham();
        MotaChitiet = sanpham.getMotasanpham();
        Idsanpham =sanpham.getIDSanpham();
        txtten.setText(TenChitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá : " + decimalFormat.format(GiaChitiet) + " Đ");
        txtmota.setText(MotaChitiet);
        Picasso.with(getApplicationContext()).load(HinhanhChitiet).into(imgchitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitiet = findViewById(R.id.toolbarchitietsanpham);
        imgchitiet = findViewById(R.id.imageviewchitetsanpham);
        txtten = findViewById(R.id.textviewtenchitietsanpham);
        txtgia = findViewById(R.id.textviewgiachitietsanpham);
        txtmota = findViewById(R.id.textviewmotachitietsanpham);
        spinner = findViewById(R.id.spinner);
        btndatmua = findViewById(R.id.buttondatmua);
    }

}
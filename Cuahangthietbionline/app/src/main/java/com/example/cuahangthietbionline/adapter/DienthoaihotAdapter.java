package com.example.cuahangthietbionline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.activity.ChiTietSanPham;
import com.example.cuahangthietbionline.model.Sanpham;
import com.example.cuahangthietbionline.ultil.CheckConnetion;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienthoaihotAdapter extends RecyclerView.Adapter<DienthoaihotAdapter.ItemHolder>{
    Context context;
    ArrayList<Sanpham> arraysanphamdienthoai;

    public DienthoaihotAdapter(Context context, ArrayList<Sanpham> arraysanphamdienthoai) {
        this.context = context;
        this.arraysanphamdienthoai = arraysanphamdienthoai;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_dienthoai_hot,null);
        ItemHolder itemHolder = new ItemHolder(v);


        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham = arraysanphamdienthoai.get(position);
        holder.txttendienthoai.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiadienthoai.setText("Giá : " + decimalFormat.format(sanpham.getGiasanpham())+ " Đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).into(holder.imghinhdienthoai);
    }

    @Override
    public int getItemCount() {
        return arraysanphamdienthoai.size();
    }

    public  class  ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhdienthoai;
        public TextView txttendienthoai,txtgiadienthoai;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhdienthoai = itemView.findViewById(R.id.imageviewsanphamdienthoai);
            txttendienthoai = itemView.findViewById(R.id.textviewtensanphamdienthoai);
            txtgiadienthoai = itemView.findViewById(R.id.textviewgiasanphamdienthoai);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(context, ChiTietSanPham.class);
                    intent.putExtra("thongtinsanpham",arraysanphamdienthoai.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnetion.ShowToast_Short(context,arraysanphamdienthoai.get(getPosition()).getTensanpham());
                    context.startActivity(intent);
                }
            });
        }
    }

}
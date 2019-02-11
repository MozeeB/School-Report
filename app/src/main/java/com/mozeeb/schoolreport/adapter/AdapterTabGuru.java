package com.mozeeb.schoolreport.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.guru.read.DataItemGuru;
import com.mozeeb.schoolreport.user.tabDaftar.guru.DetailGuru;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterTabGuru extends RecyclerView.Adapter<AdapterTabGuru.MyViewHolder> {


    @BindView(R.id.btn_overFlof_guru)
    ImageButton btnOverFlofGuru;
    private Context context;
    private List<DataItemGuru> dataItemGurus;

    public AdapterTabGuru(Context context, List<DataItemGuru> dataItemGurus) {
        this.context = context;
        this.dataItemGurus = dataItemGurus;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_guru, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,final int i) {
        myViewHolder.tvNamaGuru.setText(dataItemGurus.get(i).getNama());
        myViewHolder.tvNotelpGuru.setText(dataItemGurus.get(i).getNoTelp());
        myViewHolder.tvAlamatGuru.setText(dataItemGurus.get(i).getAlamat());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gur = new Intent(context, DetailGuru.class);
                gur.putExtra("nama", dataItemGurus.get(i).getNama());
                gur.putExtra("umur", dataItemGurus.get(i).getUmur());
                gur.putExtra("tgl", dataItemGurus.get(i).getTglLahir());
                gur.putExtra("notelp", dataItemGurus.get(i).getNoTelp());
                gur.putExtra("alamat", dataItemGurus.get(i).getAlamat());
                gur.putExtra("img", dataItemGurus.get(i).getFoto());
                context.startActivity(gur);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItemGurus.size();
    }

    @OnClick(R.id.btn_overFlof_guru)
    public void onViewClicked() {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_daftarguru)
        ImageView ivDaftarguru;
        @BindView(R.id.tv_nama_guru)
        TextView tvNamaGuru;
        @BindView(R.id.tv_notelp_guru)
        TextView tvNotelpGuru;
        @BindView(R.id.tv_alamat_guru)
        TextView tvAlamatGuru;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

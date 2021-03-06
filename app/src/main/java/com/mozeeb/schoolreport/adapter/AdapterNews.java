package com.mozeeb.schoolreport.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.berita.read.BeritaItem;
import com.mozeeb.schoolreport.user.berita.UserDetailNewsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewHolder> {


    private Context context;
    private List<BeritaItem> dataItemsNewBeritas;

    public AdapterNews(Context context, List<BeritaItem> dataItemsNewBeritas) {
        this.context = context;
        this.dataItemsNewBeritas = dataItemsNewBeritas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final String URL = "https://lombaidn.000webhostapp.com/apisekolah/foto/";
        BeritaItem dataItemBerita = dataItemsNewBeritas.get(i);
        Glide.with(context).load(URL + dataItemBerita.getGambar()).into(myViewHolder.ivNews);
        myViewHolder.tvJudul.setText(dataItemBerita.getJudul());
        myViewHolder.tvTglTerbit.setText(dataItemBerita.getTglTerbit());
        myViewHolder.tvPenerbit.setText(dataItemBerita.getPenerbit());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, UserDetailNewsActivity.class);
                in.putExtra("img", dataItemsNewBeritas.get(i).getGambar());
                in.putExtra("konten", dataItemsNewBeritas.get(i).getKonten());
                in.putExtra("tgl", dataItemsNewBeritas.get(i).getTglTerbit());
                in.putExtra("penerbit",dataItemsNewBeritas.get(i).getPenerbit());
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItemsNewBeritas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_news)
        ImageView ivNews;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_tgl_terbit)
        TextView tvTglTerbit;
        @BindView(R.id.tv_penerbit)
        TextView tvPenerbit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

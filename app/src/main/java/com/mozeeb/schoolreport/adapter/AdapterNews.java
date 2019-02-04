package com.mozeeb.schoolreport.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.berita.read.DataItemBerita;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewHolder> {


    private Context context;
    private List<DataItemBerita> dataItemsNewBeritas;

    public AdapterNews(Context context, List<DataItemBerita> dataItemsNewBeritas) {
        this.context = context;
        this.dataItemsNewBeritas = dataItemsNewBeritas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DataItemBerita dataItemBerita = dataItemsNewBeritas.get(i);
        Glide.with(context).load(dataItemBerita.getGambar()).into(myViewHolder.ivNews);
        myViewHolder.tvJudul.setText(dataItemBerita.getJudul());
        myViewHolder.tvTglTerbit.setText(dataItemBerita.getTglTerbit());
        myViewHolder.tvPenerbit.setText(dataItemBerita.getPenerbit());


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

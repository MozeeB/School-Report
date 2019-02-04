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
import com.mozeeb.schoolreport.model.kegiatan.read.DataItemKegiatan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterKegiatan extends RecyclerView.Adapter<AdapterKegiatan.MyViewHolder> {


    private Context context;
    private List<DataItemKegiatan> dataItemKegiatans;

    public AdapterKegiatan(Context context, List<DataItemKegiatan> dataItemKegiatans) {
        this.context = context;
        this.dataItemKegiatans = dataItemKegiatans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_agenda, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        Glide.with(context).load(dataItemKegiatans.get(i).getFoto()).into(myViewHolder.ivKegiatan);
        myViewHolder.tvJudulKegiatan.setText(dataItemKegiatans.get(i).getNamaKegiatan());
        myViewHolder.tvJamKegiatan.setText(dataItemKegiatans.get(i).getJam());
        myViewHolder.tvLokasiKegiatan.setText(dataItemKegiatans.get(i).getLokasi());

    }

    @Override
    public int getItemCount() {
        return dataItemKegiatans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_kegiatan)
        ImageView ivKegiatan;
        @BindView(R.id.tv_judul_kegiatan)
        TextView tvJudulKegiatan;
        @BindView(R.id.textview)
        TextView textview;
        @BindView(R.id.tv_jam_kegiatan)
        TextView tvJamKegiatan;
        @BindView(R.id.textView8)
        TextView textView8;
        @BindView(R.id.tv_lokasi_kegiatan)
        TextView tvLokasiKegiatan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

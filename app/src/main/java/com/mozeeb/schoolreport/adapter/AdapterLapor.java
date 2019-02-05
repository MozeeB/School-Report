package com.mozeeb.schoolreport.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.laporan.read.DataItemLapor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterLapor extends RecyclerView.Adapter<AdapterLapor.MyViewHolder> {


    @BindView(R.id.btn_overFlof)
    ImageButton btnOverFlof;
    private List<DataItemLapor> dataLaporan;
    private Context context;

    public AdapterLapor(List<DataItemLapor> dataLaporan, Context context) {
        this.dataLaporan = dataLaporan;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_laporan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Glide.with(context).load(dataLaporan.get(i).getFoto()).into(myViewHolder.ivLapor);
        myViewHolder.tvNamaSiswa.setText(dataLaporan.get(i).getNama());
        myViewHolder.tvKelas.setText(dataLaporan.get(i).getKelas());
        myViewHolder.tv_melanggar.setText(dataLaporan.get(i).getMelanggar());
    }

    @Override
    public int getItemCount() {
        return dataLaporan.size();
    }

    @OnClick(R.id.btn_overFlof)
    public void onViewClicked() {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_lapor)
        ImageView ivLapor;
        @BindView(R.id.tv_namaSiswa)
        TextView tvNamaSiswa;
        @BindView(R.id.tv_kelas)
        TextView tvKelas;
        @BindView(R.id.tv_melanggar)
        TextView tv_melanggar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

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
import com.mozeeb.schoolreport.model.laporan.read.LaporanItem;
import com.mozeeb.schoolreport.user.laporan.UserDetailsLaporan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class AdapterLapor extends RecyclerView.Adapter<AdapterLapor.MyViewHolder> {



    private List<LaporanItem> dataLaporan;
    private Context context;

    public AdapterLapor(List<LaporanItem> dataLaporan, Context context) {
        this.dataLaporan = dataLaporan;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_laporan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        final String URL = "https://lombaidn.000webhostapp.com/apisekolah/foto/";
        Glide.with(context).load(URL + dataLaporan.get(i).getFoto()).into(myViewHolder.ivLapor);
        myViewHolder.tvNamaSiswa.setText(dataLaporan.get(i).getNama());
        myViewHolder.tvKelas.setText(dataLaporan.get(i).getKelas());
        myViewHolder.tv_melanggar.setText(dataLaporan.get(i).getMelanggar());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lap = new Intent(context, UserDetailsLaporan.class);
                lap.putExtra("nama", dataLaporan.get(i).getNama());
                lap.putExtra("kelas", dataLaporan.get(i).getKelas());
                lap.putExtra("melanggar", dataLaporan.get(i).getMelanggar());
                lap.putExtra("keterangan", dataLaporan.get(i).getKeterangan());
                lap.putExtra("tgl", dataLaporan.get(i).getTglLapor());
                lap.putExtra("poin", dataLaporan.get(i).getPoin());
                lap.putExtra("pelapor", dataLaporan.get(i).getPelapor());
                lap.putExtra("wali", dataLaporan.get(i).getWali());
                lap.putExtra("img" ,dataLaporan.get(i).getFoto());
                context.startActivity(lap);
            }
        });
    }
    @Override
    public int getItemCount() {

        return dataLaporan.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_lapor)
        ImageView ivLapor;
        @BindView(R.id.tv_nama_Siswa)
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

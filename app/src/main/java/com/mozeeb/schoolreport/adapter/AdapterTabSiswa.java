package com.mozeeb.schoolreport.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.siswa.read.SiswaItem;
import com.mozeeb.schoolreport.user.tabDaftar.siswa.DetailSiswa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterTabSiswa extends RecyclerView.Adapter<AdapterTabSiswa.MyViewHolder> {



    private Context context;
    private List<SiswaItem> dataItemSiswas;


    public AdapterTabSiswa(Context context, List<SiswaItem> dataItemSiswas) {
        this.context = context;
        this.dataItemSiswas = dataItemSiswas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final String URL = "https://lombaidn.000webhostapp.com/apisekolah/foto/";


        SiswaItem dataItemSiswa = dataItemSiswas.get(i);
        myViewHolder.tvDaftarnamaSiswa.setText(dataItemSiswa.getNama());
        myViewHolder.tvDaftarkelas.setText(dataItemSiswa.getKelas());
        myViewHolder.tvDaftaralamat.setText(dataItemSiswa.getAlamat());
        Glide.with(context).load(URL + dataItemSiswa.getFoto()).into(myViewHolder.ivDaftarsiswa);

        Log.e("foto", dataItemSiswa.getFoto());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sis = new Intent(context, DetailSiswa.class);
                sis.putExtra("nama", dataItemSiswas.get(i).getNama());
                sis.putExtra("kelas", dataItemSiswas.get(i).getKelas());
                sis.putExtra("alamat",dataItemSiswas.get(i).getAlamat());
                sis.putExtra("umur", dataItemSiswas.get(i).getUmur());
                sis.putExtra("tgl", dataItemSiswas.get(i).getTglLahir());
                sis.putExtra("img", dataItemSiswas.get(i).getFoto());
                context.startActivity(sis);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItemSiswas.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_daftarsiswa)
        ImageView ivDaftarsiswa;
        @BindView(R.id.tv_DaftarnamaSiswa)
        TextView tvDaftarnamaSiswa;
        @BindView(R.id.tv_daftarkelas)
        TextView tvDaftarkelas;
        @BindView(R.id.tv_daftaralamat)
        TextView tvDaftaralamat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

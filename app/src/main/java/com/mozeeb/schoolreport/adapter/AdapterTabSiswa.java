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
import com.mozeeb.schoolreport.model.siswa.read.DataItemSiswa;
import com.mozeeb.schoolreport.user.tabDaftar.siswa.DetailSiswa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterTabSiswa extends RecyclerView.Adapter<AdapterTabSiswa.MyViewHolder> {


    @BindView(R.id.btn_daftaroverFlof_siswa)
    ImageButton btnDaftaroverFlof;
    private Context context;
    private List<DataItemSiswa> dataItemSiswas;

    public AdapterTabSiswa(Context context, List<DataItemSiswa> dataItemSiswas) {
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

        myViewHolder.tvDaftarnamaSiswa.setText(dataItemSiswas.get(i).getNama());
        myViewHolder.tvDaftarkelas.setText(dataItemSiswas.get(i).getKelas());
        myViewHolder.tvDaftaralamat.setText(dataItemSiswas.get(i).getAlamat());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sis = new Intent(context, DetailSiswa.class);
                sis.putExtra("nama", dataItemSiswas.get(i).getNama());
                context.startActivity(sis);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItemSiswas.size();
    }

    @OnClick(R.id.btn_daftaroverFlof_siswa)
    public void onViewClicked() {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_daftarsiswa)
        ImageView ivDaftarsiswa;
        @BindView(R.id.lin_lapor)
        LinearLayout linLapor;
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

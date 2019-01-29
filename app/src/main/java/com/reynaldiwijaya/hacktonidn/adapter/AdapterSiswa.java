package com.reynaldiwijaya.hacktonidn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reynaldiwijaya.hacktonidn.R;
import com.reynaldiwijaya.hacktonidn.model.view.SiswaItem;

import java.util.List;

public class AdapterSiswa extends RecyclerView.Adapter<AdapterSiswa.ViewHolder> {
    private Context context;
    private List<SiswaItem> listSiswaItems;

    public AdapterSiswa(Context context, List<SiswaItem> listSiswaItems) {
        this.context = context;
        this.listSiswaItems = listSiswaItems;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvNamaSiswa.setText(listSiswaItems.get(i).getNama());
        viewHolder.tvKelas.setText(listSiswaItems.get(i).getKelas());
        viewHolder.tvJenisPelanggaran.setText(listSiswaItems.get(i).getPelanggaran());

    }

    @Override
    public int getItemCount() {
        return listSiswaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView tvNamaSiswa, tvKelas, tvJenisPelanggaran;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaSiswa = itemView.findViewById(R.id.tv_namaSiswa);
            tvKelas = itemView.findViewById(R.id.tv_kelas);
            tvJenisPelanggaran = itemView.findViewById(R.id.tv_jenisPelanggaran);

        }
    }
}

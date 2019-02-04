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

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.laporan.read.DataItemLapor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLapor extends RecyclerView.Adapter<AdapterLapor.ViewHolder> {

    @BindView(R.id.btn_overFlof)
    ImageButton btnOverFlof;

    private Context context;
    private List<DataItemLapor> dataItemLapors;

    public AdapterLapor(Context context, List<DataItemLapor> dataItemLapors) {
        this.context = context;
        this.dataItemLapors = dataItemLapors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_laporan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvNamaSiswa.setText(dataItemLapors.get(i).getNama());
        viewHolder.tvKelas.setText(dataItemLapors.get(i).getKelas());
        viewHolder.tvPoin.setText(dataItemLapors.get(i).getPoin());

    }

    @Override
    public int getItemCount() {
        return dataItemLapors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_lapor)
        ImageView ivLapor;
        @BindView(R.id.tv_namaSiswa)
        TextView tvNamaSiswa;
        @BindView(R.id.tv_kelas)
        TextView tvKelas;
        @BindView(R.id.tv_poin)
        TextView tvPoin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

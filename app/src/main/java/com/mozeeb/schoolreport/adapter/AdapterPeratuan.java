package com.mozeeb.schoolreport.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.peraturan.read.PeraturanItem;
import com.mozeeb.schoolreport.user.peraturan.DetailPeraturan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPeratuan extends RecyclerView.Adapter<AdapterPeratuan.MyViewHolder> {


    private Context context;
    private List<PeraturanItem> dataItemPeraturanList;

    public AdapterPeratuan(Context context, List<PeraturanItem> dataItemPeraturanList) {
        this.context = context;
        this.dataItemPeraturanList = dataItemPeraturanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_peraturan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvNamaPeraturan.setText(dataItemPeraturanList.get(i).getJudul());
        myViewHolder.tvPenulisPeraturan.setText(dataItemPeraturanList.get(i).getPenulis());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPeraturan.class);
                intent.putExtra("judul", dataItemPeraturanList.get(i).getJudul());
                intent.putExtra("penulis", dataItemPeraturanList.get(i).getPenulis());
                intent.putExtra("isi", dataItemPeraturanList.get(i).getIsi());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItemPeraturanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nama_peraturan)
        TextView tvNamaPeraturan;
        @BindView(R.id.tv_penulis_peraturan)
        TextView tvPenulisPeraturan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

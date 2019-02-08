package com.mozeeb.schoolreport.user.kegiatan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.AdapterKegiatan;
import com.mozeeb.schoolreport.model.kegiatan.read.DataItemKegiatan;
import com.mozeeb.schoolreport.model.kegiatan.read.ResponseKegiatan;
import com.mozeeb.schoolreport.network.ConfigRetrofit;
import com.mozeeb.schoolreport.user.laporan.UserTambahActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAgendaFragment extends Fragment {


    @BindView(R.id.rv_kegiatan)
    RecyclerView rvKegiatan;
    Unbinder unbinder;

    private AdapterKegiatan adapterKegiatan;
    private List<DataItemKegiatan> dataItemKegiatans;

    public UserAgendaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_agenda, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataItemKegiatans = new ArrayList<>();

        FloatingActionButton fab = view.findViewById(R.id.fabMain_tambah_kegiatan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplication(), UserTambahKegiatan.class));
            }
        });
    }

    public void getKeiatanData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Get data");
        progressDialog.show();

        ConfigRetrofit.getInstance().getKegiatan().enqueue(new Callback<ResponseKegiatan>() {
            @Override
            public void onResponse(Call<ResponseKegiatan> call, Response<ResponseKegiatan> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toasty.success(getActivity(), response.message(), Toasty.LENGTH_LONG).show();

                    ResponseKegiatan responseKegiatan = response.body();
                    dataItemKegiatans = responseKegiatan.getData();
                    setUplist(dataItemKegiatans);
                }
            }

            @Override
            public void onFailure(Call<ResponseKegiatan> call, Throwable t) {
                Toasty.error(getActivity(), t.getMessage(), Toasty.LENGTH_LONG).show();

            }
        });

    }

    private void setUplist(List<DataItemKegiatan> dataItemKegiatans) {
        rvKegiatan.setHasFixedSize(true);
        rvKegiatan.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterKegiatan = new AdapterKegiatan(getActivity(), dataItemKegiatans);
        rvKegiatan.setAdapter(adapterKegiatan);
    }

    @Override
    public void onResume() {
        super.onResume();

        getKeiatanData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

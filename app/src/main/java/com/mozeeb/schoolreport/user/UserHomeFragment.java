package com.mozeeb.schoolreport.user;


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
import com.mozeeb.schoolreport.adapter.AdapterLapor;
import com.mozeeb.schoolreport.model.laporan.read.DataItemLapor;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporan;
import com.mozeeb.schoolreport.network.ApiService;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserHomeFragment extends Fragment {

//    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    Unbinder unbinder;
    ApiService apiService;

    private AdapterLapor adapterLaporan;
    private AdapterLapor adapter;

    private List<DataItemLapor> dataItemsLaporanLapor;

    public UserHomeFragment() {
        // Required empty public constructor
    }

    String[] nama = {"juminten", "naryo"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvMain  = view.findViewById(R.id.rv_main);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataItemsLaporanLapor = new ArrayList<>();

        FloatingActionButton fab = view.findViewById(R.id.fabMain);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplication(), UserTambahActivity.class));
            }
        });
    }


    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Get data");
        progressDialog.show();

        apiService = ConfigRetrofit.getClient().create(ApiService.class);
        Call<ResponseLaporan> call = apiService.getLaporan();
        call.enqueue(new Callback<ResponseLaporan>() {
            @Override
            public void onResponse(Call<ResponseLaporan> call, Response<ResponseLaporan> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toasty.success(getActivity(), response.toString(), Toasty.LENGTH_LONG).show();

                    ResponseLaporan responseNews = response.body();
                    dataItemsLaporanLapor = responseNews.getData();
//                    setUpList(dataItemsLaporanLapor);
                    rvMain.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                    adapter = new AdapterLapor(dataItemsLaporanLapor, getActivity());
//                    adapterLaporan = new AdapterLapor(responseNews.getData(), getActivity());
                    rvMain.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                Toasty.error(getActivity(), t.getMessage(), Toasty.LENGTH_LONG).show();

            }
        });

    }


    private void setUpList(List<DataItemLapor> dataItemsLaporanLapor) {
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterLaporan = new AdapterLapor(dataItemsLaporanLapor, getActivity());
        rvMain.setAdapter(adapterLaporan);
    }

    @Override
    public void onResume() {
        super.onResume();


        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

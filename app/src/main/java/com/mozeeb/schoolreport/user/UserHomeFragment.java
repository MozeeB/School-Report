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
import android.widget.FrameLayout;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.AdapterLapor;
import com.mozeeb.schoolreport.helper.MovableFloatingActionButton;
import com.mozeeb.schoolreport.model.laporan.read.LaporanItem;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporanRead;
import com.mozeeb.schoolreport.network.ApiService;
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
public class UserHomeFragment extends Fragment {

    //    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    Unbinder unbinder;
    ApiService apiService;

    @BindView(R.id.fabMain)
    MovableFloatingActionButton fabMain;
    @BindView(R.id.rootlyout)
    FrameLayout rootlyout;

    private AdapterLapor adapter;

    private List<LaporanItem> dataItemsLaporanLapor;

    public UserHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvMain = view.findViewById(R.id.rv_main);
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

        getData();

    }


    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        apiService = ConfigRetrofit.getClient().create(ApiService.class);
        Call<ResponseLaporanRead>  call = apiService.getLaporan();
        call.enqueue(new Callback<ResponseLaporanRead>() {
            @Override
            public void onResponse(Call<ResponseLaporanRead> call, Response<ResponseLaporanRead> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ResponseLaporanRead responseNews = response.body();
                    dataItemsLaporanLapor = responseNews.getLaporan();
//                    setUpList(dataItemsLaporanLapor);
                    rvMain.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                    adapter = new AdapterLapor(dataItemsLaporanLapor, getActivity());
//                    adapterLaporan = new AdapterLapor(responseNews.getData(), getActivity());
                    rvMain.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<ResponseLaporanRead> call, Throwable t) {
                Toasty.error(getActivity(), t.getMessage(), Toasty.LENGTH_LONG).show();

            }
        });

    }


    //    private void setUpList(List<DataItemLapor> dataItemsLaporanLapor) {
//        rvMain.setHasFixedSize(true);
//        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapterLaporan = new AdapterLapor(dataItemsLaporanLapor, getActivity());
//        rvMain.setAdapter(adapterLaporan);
//    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

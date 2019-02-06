package com.mozeeb.schoolreport.user.tabDaftar.guru;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.AdapterTabGuru;
import com.mozeeb.schoolreport.adapter.TabLayoutAdapter;
import com.mozeeb.schoolreport.model.guru.read.DataItemGuru;
import com.mozeeb.schoolreport.model.guru.read.ResponseDaftarGuru;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarGuru extends Fragment {

    @BindView(R.id.rv_tab_guru)
    RecyclerView rvTabGuru;
    Unbinder unbinder;
    private FragmentManager fragmentManager;
    private View view;
    private ProgressBar progressBar;
    private TabLayoutAdapter adapter;

    private List<DataItemGuru> dataItemGurus;
    private AdapterTabGuru adapterTabGuru;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static DaftarGuru newInstance(FragmentManager fragmentManager) {
        DaftarGuru fragment = new DaftarGuru();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_guru, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    private void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    public void getDataGuru() {
        ConfigRetrofit.getInstance().getGuru().enqueue(new Callback<ResponseDaftarGuru>() {
            @Override
            public void onResponse(Call<ResponseDaftarGuru> call, Response<ResponseDaftarGuru> response) {
                if (response.isSuccessful()) {
                    ResponseDaftarGuru responseDaftarGuru = response.body();
                    dataItemGurus = responseDaftarGuru.getData();
                    setUplist2(dataItemGurus);

                }
            }

            @Override
            public void onFailure(Call<ResponseDaftarGuru> call, Throwable t) {

            }
        });
    }

    private void setUplist2(List<DataItemGuru> dataItemGurus) {
        rvTabGuru.setHasFixedSize(true);
        rvTabGuru.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterTabGuru = new AdapterTabGuru(getActivity(), dataItemGurus);
        rvTabGuru.setAdapter(adapterTabGuru);


    }
    @Override
    public void onResume() {
        super.onResume();

        getDataGuru();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

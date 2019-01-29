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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.AdapterSiswa;
import com.mozeeb.schoolreport.model.siswa.view.ResponseSiswa;
import com.mozeeb.schoolreport.model.siswa.view.SiswaItem;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserHomeFragment extends Fragment {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    Unbinder unbinder;
    private AdapterSiswa adapterSiswa;

    private List<SiswaItem> siswaItemList;

    public UserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        siswaItemList = new ArrayList<>();

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
        progressDialog.setMessage("Get Data");
        progressDialog.show();

        ConfigRetrofit.getInstance().getAllData().enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                progressDialog.dismiss();

                if (response != null) {
                    boolean status = response.body().isStatus();
                    List<SiswaItem> siswaItemList = response.body().getSiswa();

                    if (status) setUpList(siswaItemList);
                    else Toast.makeText(getActivity(), "Tidak Ada data " + response.message() , Toast.LENGTH_SHORT).show();

                    Log.d("UserHomeFragment", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpList(List<SiswaItem> siswaItemList) {
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterSiswa = new AdapterSiswa(getActivity(), siswaItemList);
        rvMain.setAdapter(adapterSiswa);
    }

    @Override
    public void onResume() {
        super.onResume();

        siswaItemList.clear();

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

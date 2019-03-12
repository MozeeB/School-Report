package com.mozeeb.schoolreport.user.peraturan;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozeeb.schoolreport.LoginActivity;
import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.AdapterPeratuan;
import com.mozeeb.schoolreport.model.peraturan.read.PeraturanItem;
import com.mozeeb.schoolreport.model.peraturan.read.ResponsePeraturan;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

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
public class UserRulesFragment extends Fragment {

    List<PeraturanItem> dataItemPeraturanList;
    AdapterPeratuan adapterPeratuan;
    @BindView(R.id.rv_peraturan)
    RecyclerView rvPeraturan;
    Unbinder unbinder;

    private ProgressDialog progressDialog;


    public UserRulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rules, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void getPeratutan() {
        //membuat object progress dialog
        progressDialog = new ProgressDialog(getActivity());
        //menambahkan message pada loading
        progressDialog.setMessage("Loading.....");
        //menampilkan progress dialog
        progressDialog.show();
        ConfigRetrofit.getInstance().getPeraturan().enqueue(new Callback<ResponsePeraturan>() {
            @Override
            public void onResponse(Call<ResponsePeraturan> call, Response<ResponsePeraturan> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ResponsePeraturan responsePeraturan = response.body();
                    dataItemPeraturanList = responsePeraturan.getPeraturan();
                    setUpList(dataItemPeraturanList);

                }else {
                    Toasty.error(getActivity(), response.message(), Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePeraturan> call, Throwable t) {

            }
        });
    }

    private void setUpList(List<PeraturanItem> dataItemPeraturans) {
        rvPeraturan.setHasFixedSize(true);
        rvPeraturan.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterPeratuan = new AdapterPeratuan(getActivity(), dataItemPeraturans);
        rvPeraturan.setAdapter(adapterPeratuan);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();

        getPeratutan();
    }
}

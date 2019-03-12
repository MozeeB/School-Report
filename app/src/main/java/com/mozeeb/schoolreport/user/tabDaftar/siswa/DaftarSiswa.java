package com.mozeeb.schoolreport.user.tabDaftar.siswa;

import android.app.ProgressDialog;
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
import com.mozeeb.schoolreport.adapter.AdapterTabSiswa;
import com.mozeeb.schoolreport.adapter.TabLayoutAdapter;
import com.mozeeb.schoolreport.model.siswa.read.ResponseSiswa;
import com.mozeeb.schoolreport.model.siswa.read.SiswaItem;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarSiswa extends Fragment {

    @BindView(R.id.rv_tab_siswa)
    RecyclerView rvTabSiswa;
    Unbinder unbinder;
    private FragmentManager fragmentManager;
    private View view;
    private ProgressBar progressBar;
    private TabLayoutAdapter adapter;
    private List<SiswaItem> dataItemSiswa;
    private AdapterTabSiswa adapterTabSiswa;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static DaftarSiswa newInstance(FragmentManager fragmentManager) {
        DaftarSiswa fragment = new DaftarSiswa();
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
        view = inflater.inflate(R.layout.fragment_tab_siswa, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    private void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    public void getDataSiswa() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading.....");
        progressDialog.show();
        ConfigRetrofit.getInstance().getDaftarSiswa().enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toasty.success(getActivity(), "Silahkan lihat", Toasty.LENGTH_LONG).show();
                    ResponseSiswa responseSiswa = response.body();
                    dataItemSiswa = responseSiswa.getSiswa();
                    setUplist2(dataItemSiswa);

                }else{
                    Toasty.error(getActivity(), response.message(), Toasty.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Toasty.error(getActivity(), t.getMessage(), Toasty.LENGTH_LONG).show();

            }
        });
    }

    private void setUplist2(List<SiswaItem> dataItemSiswa) {
        rvTabSiswa.setHasFixedSize(true);
        rvTabSiswa.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterTabSiswa = new AdapterTabSiswa(getActivity(), dataItemSiswa);
        rvTabSiswa.setAdapter(adapterTabSiswa);


    }


    @Override
    public void onResume() {
        super.onResume();

        getDataSiswa();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

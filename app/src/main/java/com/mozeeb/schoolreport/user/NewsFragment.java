package com.mozeeb.schoolreport.user;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.AdapterNews;
import com.mozeeb.schoolreport.model.berita.read.BeritaItem;
import com.mozeeb.schoolreport.model.berita.read.ResponseBerita;
import com.mozeeb.schoolreport.network.ApiService;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

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
public class NewsFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private ApiService apiService;
    private AdapterNews adapterNews;
    private List<BeritaItem> dataItemsNewBeritas;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataItemsNewBeritas = new ArrayList<>();

//        FloatingActionButton fab = view.findViewById(R.id.fabMain_tambah_berita);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity().getApplication(), UserTambahBerita.class));
//            }
//        });
        getData2();

    }

    private void getData2() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        apiService = ConfigRetrofit.getClient().create(ApiService.class);
        Call<ResponseBerita> call = apiService.getBerita();
        call.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ResponseBerita responseNews = response.body();
                    dataItemsNewBeritas = responseNews.getBerita();
                    setUplist2(dataItemsNewBeritas);
                }else {
                    Toasty.error(getActivity(), "Failed", Toasty.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toasty.error(getActivity(), t.getMessage(), Toasty.LENGTH_LONG).show();

            }
        });

    }

    private void setUplist2(List<BeritaItem> dataItemsNewBeritas) {
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterNews = new AdapterNews(getActivity(), dataItemsNewBeritas);
        rvNews.setAdapter(adapterNews);



    }

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

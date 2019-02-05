package com.mozeeb.schoolreport.tabDaftar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.TabLayoutAdapter;

public class DaftarSiswa extends Fragment {

    private FragmentManager fragmentManager;
    private View view;
    private ProgressBar progressBar;
    private TabLayoutAdapter adapter;

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

        return view;
    }


    private void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }


}

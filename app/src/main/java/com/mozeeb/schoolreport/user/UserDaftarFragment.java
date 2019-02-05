package com.mozeeb.schoolreport.user;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.adapter.TabLayoutAdapter;
import com.mozeeb.schoolreport.tabDaftar.DaftarGuru;
import com.mozeeb.schoolreport.tabDaftar.DaftarSiswa;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDaftarFragment extends Fragment {

    private FragmentManager fragmentManager;
    private View view;
    private TabLayoutAdapter tabLayoutAdapter;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static UserDaftarFragment newInstance(FragmentManager fragmentManager) {
        UserDaftarFragment fragment = new UserDaftarFragment();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }



    public UserDaftarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_daftar, container, false);
        setPager(view);
        return view;
    }
    private void setPager(View view){
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_containerELearning);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tl_tabsAbsence);
        tabs.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager());
        tabLayoutAdapter.addFragment(DaftarSiswa.newInstance(fragmentManager), "Guru");
        tabLayoutAdapter.addFragment(DaftarGuru.newInstance(fragmentManager), "Siswa");
        viewPager.setAdapter(tabLayoutAdapter);
    }

}

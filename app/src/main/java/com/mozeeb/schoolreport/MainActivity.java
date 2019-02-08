package com.mozeeb.schoolreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mozeeb.schoolreport.user.NewsFragment;
import com.mozeeb.schoolreport.user.UserProfilActivity;
import com.mozeeb.schoolreport.user.kegiatan.UserAgendaFragment;
import com.mozeeb.schoolreport.user.UserDaftarFragment;
import com.mozeeb.schoolreport.user.UserHomeFragment;
import com.mozeeb.schoolreport.user.UserRulesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_semua);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("School Report");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserHomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.profil:
            intent = new Intent(this, UserProfilActivity.class);
            startActivity(intent);
            finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_rules:
                    selectedFragment = new UserRulesFragment();
                    break;
                case R.id.navigation_home:
                    selectedFragment = new UserHomeFragment();
                    break;
                case R.id.navigation_news:
                    selectedFragment = new NewsFragment();
                    break;
                case R.id.navigation_agenda:
                    selectedFragment = new UserAgendaFragment();
                    break;
                case R.id.navigation_daftar:
                    selectedFragment = new UserDaftarFragment();
                    break;
            }
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

}

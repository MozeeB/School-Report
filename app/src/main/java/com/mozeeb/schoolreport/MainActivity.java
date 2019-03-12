package com.mozeeb.schoolreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mozeeb.schoolreport.SPreferenced.Spereferen;
import com.mozeeb.schoolreport.user.NewsFragment;
import com.mozeeb.schoolreport.user.UserAboutActivity;
import com.mozeeb.schoolreport.user.UserProfilActivity;
import com.mozeeb.schoolreport.user.kegiatan.UserAgendaFragment;
import com.mozeeb.schoolreport.user.UserDaftarFragment;
import com.mozeeb.schoolreport.user.UserHomeFragment;
import com.mozeeb.schoolreport.user.peraturan.UserRulesFragment;

public class MainActivity extends AppCompatActivity {

    private Spereferen spereferen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_semua);
        setSupportActionBar(toolbar);

        //handle error
//        ForceCloseDebugger.handle(this);

        getSupportActionBar().setTitle("School Report");

        spereferen = new Spereferen(this);


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
                break;
            case R.id.about:
                startActivity(new Intent(this, UserAboutActivity.class));
                break;
            case R.id.logout:
                alertDialogShow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void alertDialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Tutup aplikasi ini ?")
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        spereferen.Logout();


                    }
                });
        AlertDialog buil = builder.create();
        buil.show();
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

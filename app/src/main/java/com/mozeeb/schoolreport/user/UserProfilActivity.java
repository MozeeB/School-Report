package com.mozeeb.schoolreport.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.SPreferenced.Spereferen;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfilActivity extends AppCompatActivity {

//    @BindView(R.id.img_profil)
//    CircleImageView imgProfil;
    @BindView(R.id.tv_nama_profil)
    TextView tvNamaProfil;
    @BindView(R.id.tv_username_profile)
    TextView tvUsernameProfile;
    @BindView(R.id.tv_alamat_profil)
    TextView tvAlamatProfil;
    @BindView(R.id.tv_email_profil)
    TextView tvEmailProfil;
//    @BindView(R.id.pengenal1)
//    ImageView pengenal1;
    @BindView(R.id.tv_notelp_profil)
    TextView tvNotelpProfil;
    @BindView(R.id.tv_kelamin_profil)
    TextView tvKelaminProfil;

    private Spereferen spereferen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);
        ButterKnife.bind(this);

        spereferen = new Spereferen(this);

        tvNamaProfil.setText(spereferen.getNamaUser());
        tvUsernameProfile.setText(spereferen.getUsername());
        tvAlamatProfil.setText(spereferen.getAlamatPref());
        tvEmailProfil.setText(spereferen.getEmailPref());
        tvNotelpProfil.setText(spereferen.getTelp());
        tvKelaminProfil.setText(spereferen.getKelamin());
    }
}

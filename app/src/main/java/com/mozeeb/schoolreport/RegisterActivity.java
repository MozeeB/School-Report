package com.mozeeb.schoolreport;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mozeeb.schoolreport.model.register.ResponseRegister;
import com.mozeeb.schoolreport.network.ApiService;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.edt_nama_register)
    EditText edtNamaRegister;
    @BindView(R.id.edt_username_register)
    EditText edtUsernameRegister;
    @BindView(R.id.edt_notelp_register)
    EditText edtNotelpRegister;
    @BindView(R.id.edt_alamat_register)
    EditText edtAlamatRegister;
    @BindView(R.id.edt_email_register)
    EditText edtEmailRegister;
    @BindView(R.id.spinner_kelamin_register)
    Spinner spinnerKelaminRegister;
    @BindView(R.id.edt_password_register)
    EditText edtPasswordRegister;
    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;
    @BindView(R.id.spinner_level)
    Spinner spinnerLevel;
    @BindView(R.id.img_upload)
    ImageView imgUpload;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private ApiService apiService;

    Uri selectedImage;
    private static final int REQUEST_CAMERA = 1888;
    private static final int SELECT_FILE = 1887;
    private static final int PICK_FROM_GALLERY = 2;
    String filePath = "";
    private String encodedImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        spinnerKelamin();
        spinnerLevel();
    }

    @OnClick({R.id.btn_upload, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                break;
            case R.id.btn_register:
                registerUser();
                break;
        }
    }

    public void spinnerKelamin() {

        List<String> adapter = new ArrayList<>();
        adapter.add("Laki - laki");
        adapter.add("Perempuan");
        adapter.add("Lainnya");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKelaminRegister.setAdapter(dataAdapter);
    }

    public void spinnerLevel() {

        List<String> adapter = new ArrayList<>();
        adapter.add("Guru");
        adapter.add("Wali murid");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(dataAdapter);
    }


    private void registerUser() {
        apiService = ConfigRetrofit.getClient().create(ApiService.class);
        Call<ResponseRegister> call = apiService.postRegister(edtNamaRegister.getText().toString(), edtUsernameRegister.getText().toString(), edtNotelpRegister.getText().toString(), edtAlamatRegister.getText().toString(), edtEmailRegister.getText().toString(), spinnerKelaminRegister.toString(), edtPasswordRegister.getText().toString(), imgUpload.toString(), spinnerLevel.toString());
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    Toasty.success(RegisterActivity.this, "Daftar Sukses!!!", Toasty.LENGTH_LONG).show();
                } else {
                    Toasty.error(RegisterActivity.this, "Gagal Register", Toasty.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toasty.error(RegisterActivity.this, "Gagal Register", Toasty.LENGTH_LONG).show();

            }
        });

    }

    @OnClick(R.id.img_upload)
    public void onViewClicked() {
        ChooseGallerOrCamera();
    }
    private void ChooseGallerOrCamera() {
        final CharSequence[] items = { "Choose from Gallery",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    File photo = null;
                    try
                    {
                        // place where to store camera taken picture
                        photo = this.createTemporaryFile("picture", ".jpg");
                        photo.delete();
                    }
                    catch(Exception e)
                    {

                    }
                    selectedImage = Uri.fromFile(new File("/sdcard/sample.jpg"));
                    cameraIntent.putExtra("ket","1");
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImage);
                    startActivityForResult(cameraIntent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*"); //set type for files (image type)
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

            private File createTemporaryFile(String part, String ext) throws Exception
            {
                File tempDir= Environment.getExternalStorageDirectory();
                tempDir=new File(tempDir.getAbsolutePath()+"/.temp/");
                if(!tempDir.exists())
                {
                    tempDir.mkdirs();
                }
                return File.createTempFile(part, ext, tempDir);
            }

        });
        builder.show();
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                ContentResolver cr = this.getContentResolver();
                Bitmap photo=null;
                try {
                    photo = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                selectedImage = getImageUri(getApplicationContext(), photo);
//                filePath=getRealPathFromURI(selectedImage);
//                setImageView(filePath);

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }else if(requestCode==PICK_FROM_GALLERY){
            if (resultCode==RESULT_OK){
                selectedImage = data.getData();
//                tampil_gambar_sk_hilang.setImageURI(selectedImage);
                filePath = getRealPathFromURIPath(selectedImage, this);
                setImageView(filePath);
            }
        }
    }


    private void setImageView(String filepath){
        File imgFile = new  File(filepath);
        Bitmap bm = BitmapFactory.decodeFile(filepath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] ba = baos.toByteArray();
        encodedImage = Base64.encodeToString(ba, Base64.DEFAULT);

        if(imgFile.exists()){

            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            File f=new File(filepath);
            Bitmap b = null;
            try {
                FileInputStream fis = new FileInputStream(f);
                BitmapFactory.decodeStream(fis, null, o);
                fis.close();

                float sc = 0.0f;
                int scale = 1;
                //if image height is greater than width
                if (o.outHeight > o.outWidth) {
                    sc = o.outHeight / 400;
                    scale = Math.round(sc);
                }
                //if image width is greater than height
                else {
                    sc = o.outWidth / 400;
                    scale = Math.round(sc);
                }

                // Decode with inSampleSize
                BitmapFactory.Options o2 = new BitmapFactory.Options();
                o2.inSampleSize = scale;
                fis = new FileInputStream(f);
                b = BitmapFactory.decodeStream(fis, null, o2);
//                if (ketImage==1) {
//                    tampil_gambar_rt.setImageBitmap(b);
//                    listOfImagesPath.set(0,filepath);
//                }else if (ketImage==2) {
//                    tampil_gambar_kk.setImageBitmap(b);
//                    listOfImagesPath.set(1,filepath);
//                }else if (ketImage==3) {
//                    tampil_gambar_nikah.setImageBitmap(b);
//                    listOfImagesPath.set(2,filepath);
//                }else if (ketImage==4) {
//                    tampil_gambar_akta.setImageBitmap(b);
//                    listOfImagesPath.set(3,filepath);
//                }else if (ketImage==5) {
//                    tampil_gambar_master_kk.setImageBitmap(b);
//                    listOfImagesPath.set(4,filepath);
//                }
                imgUpload.setImageBitmap(b);

                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}

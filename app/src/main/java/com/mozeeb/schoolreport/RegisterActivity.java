package com.mozeeb.schoolreport;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mozeeb.schoolreport.model.register.ResponseRegister;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
    @BindView(R.id.edt_email_register)
    EditText edtEmailRegister;
    @BindView(R.id.edt_username_register)
    EditText edtUsernameRegister;
    @BindView(R.id.edt_nama_register)
    EditText edtNamaRegister;
    @BindView(R.id.edt_password_register)
    TextInputEditText edtPasswordRegister;
    @BindView(R.id.edt_confirm_password)
    TextInputEditText edtConfirmPassword;
    @BindView(R.id.edt_alamat_register)
    EditText edtAlamatRegister;
    @BindView(R.id.edt_notelp_register)
    EditText edtNotelpRegister;
    @BindView(R.id.spinner_kelamin_register)
    Spinner spinnerKelaminRegister;
//    @BindView(R.id.spinner_level)
//    Spinner spinnerLevel;
//    @BindView(R.id.imgfotoprofile)
//    ImageView imgfotoprofile;
//    @BindView(R.id.btn_upload)
//    Button btnUpload;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private Uri filepath;
    private String mediapath;
    private Bitmap mPhoto;
    private Context context;
    String part_image;
    final int REQUEST_GALLERY = 9544;
    public final int REQ_CHOOSE_FILE_REGISTER = 100;
    public static final String UPLOAD_REGISTER_URL = "https://lombaidn.000webhostapp.com/apisekolah/user/register.php";
//public static final String UPLOAD_REGISTER_URL = "http://192.168.71.53/apisekolah/user/register.php";

    //permission granted
    private int STORAGE_PERMISSION_CODE = 1;
    private String isiKelamin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        spinnerKelamin();
//        spinnerLevel();
    }

    @OnClick({R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.btn_upload:
//                ChooseImage(REQ_CHOOSE_FILE_REGISTER);
//                break;
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

        spinnerKelaminRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (selection.equals("Laki - Laki")){
                        isiKelamin = "Laki - Laki";
                    }else if (selection.equals("Perempuan")){
                        isiKelamin = "Perempuan";
                    }else if (selection.equals("Lainnya")){
                        isiKelamin = "Lainnya";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

//    public void spinnerLevel() {
//
//        List<String> adapter = new ArrayList<>();
//        adapter.add("Guru");
//        adapter.add("Wali murid");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerLevel.setAdapter(dataAdapter);
//    }


    private void registerUser() {

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();
        String nama = edtNamaRegister.getText().toString();
        String username = edtUsernameRegister.getText().toString();
        String no_telp = edtNotelpRegister.getText().toString();
        String alamat = edtAlamatRegister.getText().toString();
        String email = edtEmailRegister.getText().toString();
        String pasword = edtPasswordRegister.getText().toString();
        String confirm_password = edtConfirmPassword.getText().toString();
        ConfigRetrofit.getInstance().postRegister(nama, username, no_telp, alamat, email, isiKelamin, pasword, confirm_password)
                .enqueue(new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                        progressDialog.dismiss();
                        if (response.body().getSuccess() == 1){
                            Toasty.success(RegisterActivity.this, response.message(),Toasty.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else {
                            Log.i("ini error mas", response.message());
                            Toasty.error(RegisterActivity.this, response.message(), Toasty.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable t) {
                        Log.i("errornya disni ua", t.getMessage());

                    }
                });


    }


    private void ChooseImage(int requestCode){
        Intent toGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(toGalery, requestCode);
        Log.i("Gallery", "Masuk Gallery");

        if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toasty.success(RegisterActivity.this, "You have already granted this permission!", Toasty.LENGTH_SHORT).show();
        } else {
            requestStoragePermission();
        }
    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(RegisterActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toasty.success(RegisterActivity.this, "Permission GRANTED", Toasty.LENGTH_SHORT).show();

            }else {
                Toasty.error(RegisterActivity.this, "Permission DENIED", Toasty.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == REQ_CHOOSE_FILE_REGISTER){
                if (data.getData() != null){
                    filepath = data.getData();
//                    Uri seletedImage = data.getData();
                    Log.i("datanya disini",filepath.toString());
                }
                try {
                    mPhoto = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
//                    imgfotoprofile.setImageBitmap(mPhoto);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String getPath(Uri filepath){
        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images
                .Media._ID +  " = ? ", new String[]{document_id}, null);
//        String provider = "com.android.providers.media.MediaProvider";
//
//        Uri uri = Uri.parse("content://media/external/images/media");
//
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);


        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;


    }



    ///----------------//////----------////
    //encodefilebase64
    public static String encodeFileBase64(String filePath) {
        File file = new File(filePath); //file Path
        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
            for (int j = 0; j < b.length; j++) {
                System.out.print((char) b[j]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        } catch (IOException e1) {
        //System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }

        byte[] byteFileArray = new byte[0];
        try {
            byteFileArray = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String base64String = "";
        if (byteFileArray.length > 0) {
            base64String = Base64.encodeToString(byteFileArray, Base64.NO_WRAP);
        //Log.i("File Base64 string", "IMAGE PARSE ==>" + base64String);
        }

        return base64String;
    }
    //getPath
    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }


//    //create folder
//    public static void createFolder() {
//        File folder = new File(BASE_DIR + EXTERNAL_DIR_FILES);
//        File pictures = new File(BASE_DIR + PICTURES_DIR_FILES);
//        File imageFolder = new File(imagesPath);
//        if (!folder.exists()) {
//            folder.mkdir();
//        }
//
//        if (!imageFolder.exists()) {
//            imageFolder.mkdir();
//        }
//
//        if (!pictures.exists()) {
//            pictures.mkdir();
//        }
//    }
//
//
//
//    public static File compressFoto(Context context, File actualImage) {
//        final String path = imagesPath;
//
//        File compressedImage = new Compressor.Builder(context)
//                .setMaxWidth(1280)
//                .setMaxHeight(1024)
//                .setQuality(85)
//                .setCompressFormat(Bitmap.CompressFormat.JPEG)
//                .setDestinationDirectoryPath(path)
//                .build()
//                .compressToFile(actualImage);
//
//        deleteRecursive(actualImage);
//
//        return compressedImage;
//    }

    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

    public static Uri convertFileToContentUri(Context context, File file) throws Exception {
        //Uri localImageUri = Uri.fromFile(localImageFile); // Not suitable as it's not a content Uri
        ContentResolver cr = context.getContentResolver();
        String imagePath = file.getAbsolutePath();
        String imageName = null;
        String imageDescription = null;
        String uriString = MediaStore.Images.Media.insertImage(cr, imagePath, imageName, imageDescription);
        return Uri.parse(uriString);
    }

    public static String getMimeTypeFromUri(Context context, Uri uri) {
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String type = mime.getExtensionFromMimeType(cR.getType(uri));

        return type;
    }





}

package com.mozeeb.schoolreport;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mozeeb.schoolreport.model.register.ResponseRegister;
import com.mozeeb.schoolreport.network.ApiService;
import com.mozeeb.schoolreport.network.ConfigRetrofit;
import com.squareup.picasso.Picasso;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @BindView(R.id.spinner_level)
    Spinner spinnerLevel;
    @BindView(R.id.img_upload)
    ImageView imgUpload;
    @BindView(R.id.imgfotoprofile)
    ImageView imgfotoprofile;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private ApiService apiService;

    String part_image;
    final int REQUEST_GALLERY = 9544;

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
                ChooseGallerOrCamera();
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
        File imagefile = new File(part_image);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form_file"), imagefile);
        MultipartBody.Part part = MultipartBody.Part.createFormData("foto", imagefile.getName(), requestBody);

        apiService = ConfigRetrofit.getClient().create(ApiService.class);
        Call<ResponseRegister> call = apiService.postRegister(edtNamaRegister.getText().toString(), edtUsernameRegister.getText().toString(), edtNotelpRegister.getText().toString(), edtAlamatRegister.getText().toString(), edtEmailRegister.getText().toString(), spinnerKelaminRegister.toString(), edtPasswordRegister.getText().toString(), part, spinnerLevel.toString());
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

    private void ChooseGallerOrCamera() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Open Gallery"), REQUEST_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {

                Uri selectedImageURI = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImageURI, imageprojection, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image = cursor.getString(indexImage);

                    if (part_image != null) {
                        File image = new File(part_image);
                        imgfotoprofile.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                        Picasso.with(RegisterActivity.this).load(selectedImageURI).noPlaceholder().centerCrop().fit()
                                .into((ImageView) findViewById(R.id.imgfotoprofile));
                    } else {
                        Log.d("img", "gambar tidak ada");
                    }
                }
            }

        }
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

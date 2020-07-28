package com.example.photoeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageActivity;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity{

    //private final String TAG = getClass().getSimpleName();

    //private static final int REQUEST_FROM_ALBUM = 2002;
    //private static final int REQUEST_IMAGE_CROP = 2003;

    //private Button backbtn;
    //private Button cropbtn;
    private Button checkbtn;
    ImageView imageView;

    //String mCurrentPhotoPath;   //현재 사용중인 사진의 경로
    //Uri photoURI, albumURI;     //파일이 가지고 있는 경로

    //boolean isAlbum = false;    //크롭할 때 카메라에서 가져온것인지 앨범인지 확인



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        //뒤로가기 버튼
        /*
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CropActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        */

        //이미지뷰
        imageView=findViewById(R.id.imageViewId);

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);

        //이미지 넘기기
        checkbtn = (Button) findViewById(R.id.checkbtn);
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                float scale = (float) (1024/(float)bitmap.getWidth());
                int image_w = (int) (bitmap.getWidth() * scale);
                int image_h = (int) (bitmap.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(EditActivity.this, EditMain.class);
                //intent.putExtra("string", "intent 연습");
                //intent.putExtra("integer", 300);
               // intent.putExtra("double", 3.141592 );
                intent.putExtra("image", byteArray);

                startActivity(intent);
            }
        });
    }

    //파일 생성
    /*
    private  File createImageFile() throws  IOException{
        //이미지 파일 네임 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pathvalue/"+imageFileName);

        //파일 저장
        mCurrentPhotoPath = storageDir.getAbsolutePath();
        Log.i("mCurrentPhotoPath", mCurrentPhotoPath);
        return storageDir;

    }

    //앨범 열기
    public void getAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_FROM_ALBUM);
    }

    //이미지 자르기
    public void cropImage(){
        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        cropIntent.setDataAndType(photoURI, "image/*");
        cropIntent.putExtra("outputX", 200);
        cropIntent.putExtra("outputY", 200);
        cropIntent.putExtra("scale", true);

        if(isAlbum == true){
            cropIntent.putExtra("output", albumURI);    //크롭된 이미지를 해당 경로에 저장
        }
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
    }

    //갤러리 새로고침용
    private void galleryAddPic(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.i("onActivityResult", "CALL");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

        /*
        switch (requestCode){
            case REQUEST_FROM_ALBUM:
                isAlbum = true;
                File albumFile = null;
                try{
                    albumFile = createImageFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(albumFile != null){
                    albumURI = Uri.fromFile(albumFile);
                }
                photoURI = data.getData();
                cropImage();
                break;

            case REQUEST_IMAGE_CROP:
                //크롭 후에 동기화
                galleryAddPic();
                //이미지뷰에 업로드
                //uploadFile(mCurrentPhotoPath);
                break;
        }
         */
    }
}
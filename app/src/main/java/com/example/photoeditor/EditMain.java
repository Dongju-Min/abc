package com.example.photoeditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EditMain extends AppCompatActivity {

    private Button cropbtn;
    private Button savebtn;
    private ImageView editImageViewId;
    //filterbtn, bgbtn



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main);

        //크롭화면으로 이동
        cropbtn = findViewById(R.id.cropbtn);
        cropbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditMain.this, EditActivity.class);
                startActivity(intent);
            }
        });

        //이미지 받아오기
        editImageViewId = (ImageView)findViewById(R.id.editImageViewId);
        //TextView textview = (TextView)findViewById(R.id.textview);
        Bundle extras = getIntent().getExtras();
        String s = extras.getString("string");
        int i = extras.getInt("integer");
        double d = extras.getDouble("double");
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        String str = s+"\n"+i+"\n"+Double.toString(d)+"\n";
        //textview.setText(str);
        editImageViewId.setImageBitmap(bitmap);

        //save 버튼
        savebtn = findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // saveImageToGallery();
            }
        });
    }

    //이미지 저장
    /*
    private void saveImageToGallery(){
        editImageViewId.setDrawingCacheEnabled(true);
        Bitmap b = editImageViewId.getDrawingCache();
        Images.Media.insertImage(getActivity().getContentResolver(), b,title, description);
    }
     */

}
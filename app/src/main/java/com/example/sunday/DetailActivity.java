package com.example.sunday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent in = getIntent();
        int index = in.getIntExtra("ITEM_INDEX",-1);

        if (index > -1){
            int pic = getIgm(index);
            ImageView img = (ImageView) findViewById(R.id.imageView4);
            scaleImg(img, pic);
        }
    }
    private int getIgm(int index){
        switch (index){
            case 0: return R.drawable.ssensodyne;
            case 1: return R.drawable.pplay;
            case 2: return R.drawable.coke;
            case 3: return R.drawable.clover;
            case 4: return R.drawable.dairymilk;
            case 5: return R.drawable.colgate;
            default:return -1;
        }
    }

    private void scaleImg(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth){
            int ratio = Math.round((float)imgWidth / (float)screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaledImg  = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }
}

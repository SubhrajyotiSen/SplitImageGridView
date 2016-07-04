package com.example.subhrajyoti.splitimage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;



import android.app.Activity;

import android.content.Intent;

import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button split = (Button) findViewById(R.id.small_image);
        assert split != null;
        split.setOnClickListener(small_listner);

    }



    public OnClickListener small_listner = new OnClickListener() {

        public void onClick(View v) {

            ImageView image = (ImageView) findViewById(R.id.source_image);

            //Enter number of blocks here (should be a perfect quare)
            int numberOfBlocks = 16;
            splitImage(image,numberOfBlocks);
        }

    };


    private void splitImage(ImageView image, int numberOfBlocks) {

        int rows,cols;
        int smallimage_Height,smallimage_Width;

        BitmapDrawable mydrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = mydrawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rows = cols = (int) Math.sqrt(numberOfBlocks);
        smallimage_Height = bitmap.getHeight()/rows;
        smallimage_Width = bitmap.getWidth()/cols;


        int yCo = 0;
        Intent intent = new Intent(MainActivity.this, SmallImageActivity.class);


        for(int x=0; x<rows; x++){
            int xCo = 0;
            for(int y=0; y<cols; y++){
                try {
                    Bitmap bmp =Bitmap.createBitmap(scaledBitmap, xCo, yCo, smallimage_Width, smallimage_Height);
                    String filename = "bitmap"+x+""+y+".png";
                    FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    stream.close();
                    bmp.recycle();

                    intent.putExtra("image"+x+""+y, filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                xCo += smallimage_Width;
            }
            yCo+= smallimage_Height;
        }
        intent.putExtra("num",rows);
        startActivity(intent);

    }

}


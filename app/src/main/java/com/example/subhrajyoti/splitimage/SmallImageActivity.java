package com.example.subhrajyoti.splitimage;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.util.ArrayList;



import android.app.Activity;

import android.graphics.Bitmap;

import android.os.Bundle;

import android.widget.GridView;

public class SmallImageActivity extends Activity {

    public void onCreate(Bundle bundle){

        super.onCreate(bundle);
        setContentView(R.layout.activity_small_image);

        ArrayList<Bitmap> smallImage = new ArrayList<>();
        int num = getIntent().getIntExtra("num",3);

        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++){
                Bitmap bmp = null;
                String filename = getIntent().getStringExtra("image"+i+""+j);
                try {
                    FileInputStream is = this.openFileInput(filename);
                    bmp = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                smallImage.add(bmp);
            }
        }

        GridView image_grid = (GridView) findViewById(R.id.gridView);

        image_grid.setAdapter(new SmallImageAdapter(this, smallImage));

        image_grid.setNumColumns((int) Math.sqrt(smallImage.size()));

    }

}


package com.example.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private File imageDir;
    private String allFiles[];
    private ImageView imageViewOne;
    private ImageView imageViewTwo;
    private ImageView imageViewThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageDir = getFilesDir().getAbsoluteFile();
        imageViewOne = findViewById(R.id.smallImageOne);
        imageViewTwo = findViewById(R.id.smallImageTwo);
        imageViewThree = findViewById(R.id.smallImageThree);

        showRecentSketches();
    }
    public void onClick(View view) {
        DrawingAreaA2 mda = findViewById(R.id.drawing_area_a2);
        Bitmap b = mda.getBitmap();
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            File f = new File(getFilesDir().getAbsoluteFile() + "/IMG_" + timeStamp + ".png");
            FileOutputStream fos = new FileOutputStream(f);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        showRecentSketches();
    }

    private void showRecentSketches() {
        allFiles = imageDir.list();

        imageViewOne.setImageBitmap((BitmapFactory.decodeFile(imageDir.getAbsolutePath() + "/" + allFiles[allFiles.length - 1])));
        imageViewTwo.setImageBitmap((BitmapFactory.decodeFile(imageDir.getAbsolutePath() + "/" + allFiles[allFiles.length - 2])));
        imageViewThree.setImageBitmap((BitmapFactory.decodeFile(imageDir.getAbsolutePath() + "/" + allFiles[allFiles.length - 3])));
    }

    public void onClear(View view) {
        DrawingAreaA2 mda = findViewById(R.id.drawing_area_a2);
        mda.clear();
    }
}
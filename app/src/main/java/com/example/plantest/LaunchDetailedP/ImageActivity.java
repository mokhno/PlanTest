package com.example.plantest.LaunchDetailedP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.plantest.R;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
TouchImageView imageView;
private String imageLink;
    public static final String IMAGE = "IMAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = findViewById(R.id.touchImage);
        Bundle bundle = getIntent().getExtras();
        imageLink= (String) bundle.getString(IMAGE);
        Picasso.get().load(imageLink).into(imageView);
    }
}

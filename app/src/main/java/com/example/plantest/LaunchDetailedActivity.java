package com.example.plantest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantest.Model.Launch;
import com.squareup.picasso.Picasso;

public class LaunchDetailedActivity extends AppCompatActivity {
    private static final String TAG = "tag2";
    TextView textMissionName;
    TextView textMissionData;
    TextView textRocketName;
    TextView textWikiLink;
    TextView textVideoLink;
    TextView textRedditMedia;
    TextView textArticleLink;
    ImageView imageViewPatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detailed);
        Intent i = getIntent();
        Launch launch = (Launch) i.getSerializableExtra("ITEM");
        textMissionName = findViewById(R.id.mission_name);
        imageViewPatch = findViewById(R.id.imageViewPatch);
        textMissionData = findViewById(R.id.mission_data);
        textRocketName = findViewById(R.id.rocket_name);
        textWikiLink = findViewById(R.id.wiki_link);
        textVideoLink = findViewById(R.id.video_link);
        textRedditMedia = findViewById(R.id.reddit_link);
        textArticleLink= findViewById(R.id.article_link);
        showView(launch);
    }

    public void showView(final Launch launch) {
        try {
            textMissionData.setText(launch.getLaunchDateUtc());
            Picasso.get().load(launch.getLinks().getMissionPatch()).into(imageViewPatch);
            textMissionName.setText(launch.getMissionName());
            textRocketName.setText("Название ракеты: "+launch.getRocket().getRocketName());
            textWikiLink.setText("Стрнаица в Вики");
            textWikiLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(launch.getLinks().getWikipedia());
                    goToLink(uri);
                }
            });
            textVideoLink.setText("Видео");
            textVideoLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(launch.getLinks().getVideoLink());
                    goToLink(uri);
                }
            });
            textArticleLink.setText("Статья");
            textArticleLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(launch.getLinks().getArticleLink());
                    goToLink(uri);
                }
            });
            textRedditMedia.setText("Reddit");
            textRedditMedia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (launch.getLinks().getRedditMedia()==null){
                        Toast.makeText(LaunchDetailedActivity.this,"Ссылка на реддит появляется с двадцать первого запуска", Toast.LENGTH_LONG).show();
                    }else{
                    Uri uri = Uri.parse(launch.getLinks().getRedditMedia());
                    goToLink(uri);}
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "showView: " + e);
        }
    }

    public void goToLink(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}

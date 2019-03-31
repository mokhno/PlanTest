package com.example.plantest.LaunchDetailedP;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantest.MainActivityP.LaunchAdaper;
import com.example.plantest.Model.Launch;
import com.example.plantest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LaunchDetailedActivity extends AppCompatActivity implements DetailedAdapter.OnImageClickListaner {
    public static final String ITEM = "ITEM";
    private static final String TAG = LaunchDetailedActivity.class.getCanonicalName();
    TextView textMissionName;
    TextView textMissionData;
    TextView textRocketName;
    TextView textWikiLink;
    TextView textVideoLink;
    TextView textRedditMedia;
    TextView textArticleLink;
    ImageView imageViewPatch;
   DetailedAdapter.OnImageClickListaner mImageListener;
    private RecyclerView recycler;
    private final DetailedAdapter detailedAdapter = new DetailedAdapter();
    private Launch launch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detailed);
        Intent i = getIntent();
        launch = (Launch) i.getSerializableExtra(LaunchDetailedActivity.ITEM);
        initViews();
        Picasso.get().load(launch.getLinks().getMissionPatch()).into(imageViewPatch);
        showView(launch);
        updateView(launch);

    }


    private void initViews() {
        textMissionName = findViewById(R.id.mission_name);
        imageViewPatch = findViewById(R.id.imageViewPatch);
        textMissionData = findViewById(R.id.mission_data);
        textRocketName = findViewById(R.id.rocket_name);
        textWikiLink = findViewById(R.id.wiki_link);
        textVideoLink = findViewById(R.id.video_link);
        textRedditMedia = findViewById(R.id.reddit_link);
        textArticleLink = findViewById(R.id.article_link);
        recycler = findViewById(R.id.recycler_images);
    }

    private void updateView(Launch launch) {
        textMissionData.setText(launch.getLaunchDateUtc());
        textMissionName.setText(launch.getMissionName());
        textRocketName.setText("Название ракеты: " + launch.getRocket().getRocketName());
        textWikiLink.setText("Стрнаица в Вики");
        textVideoLink.setText("Видео");
        textArticleLink.setText("Статья");
        textRedditMedia.setText("Reddit");
        recycler.setLayoutManager(new LinearLayoutManager(LaunchDetailedActivity.this));
        recycler.setAdapter(detailedAdapter);
        if (getImageLinks().size() > 1) {
            recycler.setVisibility(View.VISIBLE);
        }
        detailedAdapter.addData(getImageLinks());
        mImageListener = LaunchDetailedActivity.this;
        Log.d(TAG, "mimagelistener  "+mImageListener);
        detailedAdapter.setmImageListener(mImageListener);
    }

    public void showView(final Launch launch) {

        textWikiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryParceURI(launch.getLinks().getWikipedia());
            }
        });
        textVideoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryParceURI(launch.getLinks().getVideoLink());
            }
        });
        textArticleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryParceURI(launch.getLinks().getArticleLink());
            }
        });
        textRedditMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (launch.getLinks().getRedditMedia() == null) {
                    Toast.makeText(LaunchDetailedActivity.this, "Ссылка на реддит появляется с двадцать первого запуска", Toast.LENGTH_LONG).show();
                } else {
                    tryParceURI(launch.getLinks().getRedditMedia());
                }
            }
        });

    }

    public List<String> getImageLinks() {
        List<String> links = launch.getLinks().getFlickrImages();

        return links;
    }

    private void tryParceURI(String uriString) {
        try {
            Uri uri = Uri.parse(uriString);
            goToLink(uri);
        } catch (Exception e) {

        }
    }

    public void goToLink(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
        @Override
    public void onItemClick(String string) {
        Intent intent = new Intent(LaunchDetailedActivity.this, ImageActivity.class);
        intent.putExtra(ImageActivity.IMAGE, string);
        startActivity(intent);

    }
}

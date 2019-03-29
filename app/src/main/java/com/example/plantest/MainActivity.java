package com.example.plantest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;

import com.example.plantest.Model.Launch;


public class MainActivity extends AppCompatActivity implements LaunchAdaper.OnItemClickListaner {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, RecyclerFragment.newInstance()).commit();

        }
    }

    @Override
    public void onItemClick(Launch launch) {
        Toast.makeText(this,"launch"+launch.getMissionName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,LaunchDetailedActivity.class);
        intent.putExtra("ITEM",launch);
        startActivity(intent);

    }
}

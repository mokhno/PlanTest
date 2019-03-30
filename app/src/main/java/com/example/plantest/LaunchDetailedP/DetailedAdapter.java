package com.example.plantest.LaunchDetailedP;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantest.Model.Launch;
import com.example.plantest.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.plantest.MainActivityP.LaunchAdaper.TAG;

public class DetailedAdapter extends RecyclerView.Adapter<DetailedHolder> {

    List<String> imagesList = new ArrayList<>();


    @NonNull
    @Override
    public DetailedHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View view = inflater.inflate(R.layout.li_images, viewGroup, false);
        return new DetailedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedHolder detailedHolder, int i) {
detailedHolder.bind(imagesList.get(i));
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }
    public void addData(List<String> links){
        imagesList.addAll(links);
        Log.d(TAG, "addData: "+imagesList);
        notifyDataSetChanged();
    }
}

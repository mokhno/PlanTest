package com.example.plantest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.plantest.Model.Launch;


import java.util.ArrayList;
import java.util.List;



public class LaunchAdaper extends RecyclerView.Adapter<LaunchHolder> {
    List<Launch> launchList = new ArrayList<>();

    public static final String TAG = "tagg";

private OnItemClickListaner mListaner;



    @NonNull
    @Override
    public LaunchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View view = inflater.inflate(R.layout.li_lauch, viewGroup, false);

        return new LaunchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LaunchHolder launchHolder, final int position) {
        launchHolder.bind(launchList.get(position));
        launchHolder.setListener(mListaner);
    }


    @Override
    public int getItemCount() {
        return launchList.size();
    }

    public void addData(List<Launch> launches) {
        launchList.addAll(launches);
        notifyDataSetChanged();

    }

    public void setListaener(OnItemClickListaner listaener) {
        mListaner = listaener;
    }

    public  interface OnItemClickListaner{
        void  onItemClick(Launch launch);
}
}

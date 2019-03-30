package com.example.plantest.MainActivityP;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantest.Model.Launch;
import com.example.plantest.Model.Links;
import com.example.plantest.R;
import com.squareup.picasso.Picasso;

public class LaunchHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "tagg";
    private TextView mName;
    private TextView mData;

    private ImageView mPatch;
    public Links link;
    public Launch mLaunch;
    private View mView;

    public LaunchHolder(@NonNull View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.tv_name);
        mData = itemView.findViewById(R.id.tv_date);
        mPatch = itemView.findViewById(R.id.iv_patch);
        mView = itemView;

    }

    public void bind(final Launch launch) {
        try {
            link = launch.getLinks();
            String string = link.getMissionPatch();
            Uri uri = Uri.parse(string);
            Log.d(TAG, "bind: " + uri);
            mName.setText(launch.getMissionName());
            mData.setText(launch.getLaunchDateUtc());
            Picasso.get().load(uri).into(mPatch);
            mLaunch = launch;
        } catch (Exception e) {
            Log.d(TAG, "bind: " + e);
        }
    }
    public void setListener(final LaunchAdaper.OnItemClickListaner listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mLaunch);
            }
        });
    }

}


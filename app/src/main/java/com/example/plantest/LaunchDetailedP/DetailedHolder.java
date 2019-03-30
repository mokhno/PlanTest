package com.example.plantest.LaunchDetailedP;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.plantest.R;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Picasso;

public class DetailedHolder extends RecyclerView.ViewHolder {

    private TouchImageView mImage;
    public DetailedHolder(@NonNull View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.imageView2);
    }
    public void bind(String string){
        Uri uri = Uri.parse(string);
        Picasso.get().load(uri).into(mImage);
    }
}

package com.example.plantest.LaunchDetailedP;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.plantest.MainActivityP.LaunchAdaper;
import com.example.plantest.R;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Picasso;
import static com.example.plantest.MainActivityP.LaunchAdaper.TAG;

public class DetailedHolder extends RecyclerView.ViewHolder {

    private ImageView mImage;
    private String mImageLink;

    public DetailedHolder(@NonNull View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.imageView2);
    }
    public void bind(String string){
        mImageLink = string;
        Uri uri = Uri.parse(string);
        Picasso.get().load(uri).into(mImage);
    }
     public void setImageListener(final DetailedAdapter.OnImageClickListaner listener){
         itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        listener.onItemClick(mImageLink);

    }
});
     }
}

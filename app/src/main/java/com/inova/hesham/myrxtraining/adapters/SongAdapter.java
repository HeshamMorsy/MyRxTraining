package com.inova.hesham.myrxtraining.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inova.hesham.myrxtraining.R;
import com.inova.hesham.myrxtraining.entities.Song;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Song> mySongList;
    private Context mContext;
    private int lastPosition = -1;

    // constructor for my adapter to set the arrayList data and the context to my private references
    public SongAdapter(ArrayList<Song> arrayList, Context context){
        mySongList = arrayList;
        mContext = context;
    }

    // method to sort the stories referring to seen stories and new ones

    @Override
    public void onViewDetachedFromWindow(@NonNull SongAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        ((SongAdapter.ViewHolder)holder).clearAnimation();
    }

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_design, viewGroup, false);
        return new SongAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindItems(mySongList.get(i));
        setAnimation(viewHolder.itemView, i);
    }


    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mySongList.size();
    }

    // this is my custom subclass from ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        // reference to the views in my layout
        private ImageView poster;
        private TextView album_name;
        private TextView artist_name;
        // reference to the view
        private View mView;

        // constructor for my view holder
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            poster = mView.findViewById(R.id.row_image);
            album_name = mView.findViewById(R.id.row_albumName);
            artist_name = mView.findViewById(R.id.row_artistName);

        }

        // this method to set data inside views
        private void bindItems(final Song item) {
            Glide.with(mContext)
                    .load(item.getCover_art())
                    .into(poster);
            album_name.setText(item.getAlbum_title());
            artist_name.setText(item.getArtist_name());
        }

        public void clearAnimation()
        {
            this.poster.clearAnimation();
        }

    }


}

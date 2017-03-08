package com.achie.photosearch.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.achie.photosearch.R;
import com.achie.photosearch.model.Photo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;

import java.util.ArrayList;
import java.util.List;

public class PhotosListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> photos = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private PhotoClickListener photoClickListener;

    public PhotosListAdapter(Context context, PhotoClickListener clickListener) {
        this.context = context;
        this.photoClickListener = clickListener;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View photoItemView = inflater.inflate(R.layout.list_item_photo, parent, false);
        viewHolder = new PhotoViewHolder(photoItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        PhotoViewHolder viewHolder = (PhotoViewHolder) vh;
        if (photos.size() > 0) {
            viewHolder.bindPhoto(photos.get(position));
        }
        return;
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void addPhotos(List<Photo> photos) {
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    public void clearPhotos() {
        this.photos.clear();
        notifyDataSetChanged();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View itemView;
        public ImageView photoImageView;
        public TextView titleTextView;

        public PhotoViewHolder(View v) {
            super(v);
            itemView = v;
            photoImageView = (ImageView) v.findViewById(R.id.image_view_photo);
            titleTextView = (TextView) v.findViewById(R.id.text_view_photo_title);
            v.setOnClickListener(this);
        }

        public void bindPhoto(Photo photo) {
            String title = photo.getTitle();
            if (!TextUtils.isEmpty(title)) {
                titleTextView.setText(title);
                titleTextView.setVisibility(View.VISIBLE);
            } else {
                titleTextView.setText("no title");
//                titleTextView.setVisibility(View.GONE);
            }

            String photoUtl = photo.getImageUrl('l');
            if (!TextUtils.isEmpty(photoUtl)) {
                Glide.with(context).load(new GlideUrl(photoUtl))
                        .placeholder(R.drawable.image_list_placeholder)
                        .error(R.drawable.image_list_placeholder)
                        .centerCrop()
                        .into(photoImageView);
            } else {
                photoImageView.setImageResource(R.drawable.image_list_placeholder);
            }
        }

        @Override
        public void onClick(View view) {
            if (photoClickListener != null) {
                photoClickListener.onPhotoClick(photos.get(getLayoutPosition() - 1));
            }
        }
    }

    public interface PhotoClickListener {
        void onPhotoClick(Photo photo);
    }
}

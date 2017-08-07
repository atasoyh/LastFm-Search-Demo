package com.atasoyh.lastfmartistfinder.view.customview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.Track;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class TrackView extends FrameLayout {
    public TrackView(@NonNull Context context) {
        super(context);
        init();
    }

    public TrackView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TrackView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TrackView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @BindView(R.id.sdv)
    SimpleDraweeView simpleDraweeView;

    @BindView(R.id.tv)
    TextView tv;

    private void init() {
        inflate(getContext(), R.layout.item_artist, this);
        ButterKnife.bind(this, this);
    }

    public void setTrack(Track track) {
        if (track == null) return;
        tv.setText(track.getName());
        simpleDraweeView.setImageURI(track.getLargeImageUrl());

    }

    public void setImageUrl(String imageUrl) {
        this.simpleDraweeView.setImageURI(imageUrl);
    }

    public void setTrackName(String trackName) {
        this.simpleDraweeView.setImageURI(trackName);
    }

}

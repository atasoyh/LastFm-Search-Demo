package com.atasoyh.lastfmartistfinder.view.customview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Bio;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class BioView extends FrameLayout {
    public BioView(@NonNull Context context) {
        super(context);
        init();
    }

    public BioView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BioView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BioView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.tvSummary)
    TextView tvSummary;

    boolean isSummaryVisible = true;

    private void init() {
        inflate(getContext(), R.layout.layout_bio, this);
        ButterKnife.bind(this, this);

        tvContent.setMovementMethod(new LinkMovementMethod());

        tvSummary.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSummaryVisible) {
                    tvSummary.setVisibility(View.GONE);
                    tvContent.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void setBio(Bio bio) {
        tvSummary.setText(Html.fromHtml(bio.getSummary()));
        tvContent.setText(Html.fromHtml(bio.getContent()));


    }
}

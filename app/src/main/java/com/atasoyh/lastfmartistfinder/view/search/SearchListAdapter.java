package com.atasoyh.lastfmartistfinder.view.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.view.custom.ArtistView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 30/06/2017.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    List<Artist> artistList;
    OnItemClickListener onItemClickListener;
    OnNeededLoadMoreListener onNeededLoadMoreListener;

    public SearchListAdapter(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnNeededLoadMoreListener(OnNeededLoadMoreListener onNeededLoadMoreListener) {
        this.onNeededLoadMoreListener = onNeededLoadMoreListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new ArtistView(parent.getContext());// LayoutInflater.from(parent.getContext())
        //.inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Artist item = getItem(position);
        holder.artistView.setArtist(item);
        if (onItemClickListener != null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(item);
                }
            });
        if (position > artistList.size() - 10 && onNeededLoadMoreListener != null) {
            onNeededLoadMoreListener.onNeededLoadMore();
        }

    }

    public Artist getItem(int position) {
        return artistList.get(position);
    }

    @Override
    public int getItemCount() {
        if (artistList == null) return 0;
        return artistList.size();
    }

    public void addItems(List<Artist> items) {
        if (items == null) return;
        artistList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<Artist> items) {
        this.artistList = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ArtistView artistView;

        public ViewHolder(View itemView) {
            super(itemView);
            artistView = (ArtistView) itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Artist item);
    }

    public interface OnNeededLoadMoreListener {
        void onNeededLoadMore();
    }
}

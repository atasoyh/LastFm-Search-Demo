package com.atasoyh.lastfmartistfinder.view.search.artist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.view.customview.ArtistView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atasoyh on 30/06/2017.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ArtistViewHolder> {

    List<Artist> itemList;
    OnItemClickListener onItemClickListener;
    OnNeededLoadMoreListener onNeededLoadMoreListener;

    public SearchListAdapter(List<Artist> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnNeededLoadMoreListener(OnNeededLoadMoreListener onNeededLoadMoreListener) {
        this.onNeededLoadMoreListener = onNeededLoadMoreListener;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new ArtistView(parent.getContext());// LayoutInflater.from(parent.getContext())
        //.inflate(R.layout.item_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        if (getItem(position) instanceof Artist) {
            final Artist item = (Artist)getItem(position);
            holder.artistView.setArtist(item);
            if (onItemClickListener != null)
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(item);
                    }
                });
            if (position > itemList.size() - 10 && onNeededLoadMoreListener != null) {
                onNeededLoadMoreListener.onNeededLoadMore();
            }
        }
    }


    public Artist getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public int getItemCount() {
        if (itemList == null) return 0;
        return itemList.size();
    }

    public void addItems(List<Artist> items) {
        if (items == null) return;
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<Artist> items) {
        this.itemList = items;
        notifyDataSetChanged();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {
        ArtistView artistView;

        public ArtistViewHolder(View itemView) {
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

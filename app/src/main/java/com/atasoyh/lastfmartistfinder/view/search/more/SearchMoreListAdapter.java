package com.atasoyh.lastfmartistfinder.view.search.more;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.atasoyh.lastfmartistfinder.model.LastFMDisplayableInterface;
import com.atasoyh.lastfmartistfinder.view.customview.LastFmItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atasoyh on 30/06/2017.
 */

public class SearchMoreListAdapter extends RecyclerView.Adapter<SearchMoreListAdapter.ItemViewHolder> {

    List<LastFMDisplayableInterface> itemList;
    OnItemClickListener onItemClickListener;
    OnNeededLoadMoreListener onNeededLoadMoreListener;

    public SearchMoreListAdapter(List<LastFMDisplayableInterface> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnNeededLoadMoreListener(OnNeededLoadMoreListener onNeededLoadMoreListener) {
        this.onNeededLoadMoreListener = onNeededLoadMoreListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new LastFmItemView(parent.getContext());
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (getItem(position) instanceof LastFMDisplayableInterface) {
            final LastFMDisplayableInterface item = (LastFMDisplayableInterface) getItem(position);
            holder.itemView.setItem(item);
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


    public LastFMDisplayableInterface getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public int getItemCount() {
        if (itemList == null) return 0;
        return itemList.size();
    }

    public void addItems(List<LastFMDisplayableInterface> items) {
        if (items == null) return;
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<LastFMDisplayableInterface> items) {
        this.itemList = items;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        LastFmItemView itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = (LastFmItemView) itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(LastFMDisplayableInterface item);
    }

    public interface OnNeededLoadMoreListener {
        void onNeededLoadMore();
    }
}

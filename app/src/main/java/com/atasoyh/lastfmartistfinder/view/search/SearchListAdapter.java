package com.atasoyh.lastfmartistfinder.view.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.SearchItems;
import com.atasoyh.lastfmartistfinder.model.Track;
import com.atasoyh.lastfmartistfinder.view.customview.AlbumView;
import com.atasoyh.lastfmartistfinder.view.customview.ArtistView;
import com.atasoyh.lastfmartistfinder.view.customview.TrackView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 30/06/2017.
 */

public class SearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER_VIEW_TYPE = 1;
    public static final int ARTIST_VIEW_TYPE = 2;
    public static final int ALBUM_VIEW_TYPE = 3;
    public static final int TRACK_VIEW_TYPE = 4;

    private List<Artist> artisList;
    private List<Album> albumList;
    private List<Track> trackList;
    private List<Integer> titleList;
    private List<Integer> titleIndexList;
    private OnItemClickListener onItemClickListener;
    private OnMoreClickLister onMoreClickLister;

    int itemCount;

    public SearchListAdapter(SearchItems itemList) {
        titleList = new ArrayList<>();
        titleIndexList = new ArrayList<>();

        this.artisList = itemList.getArtistList();
        if (artisList != null) {
            titleList.add(R.string.artist);
            titleIndexList.add(itemCount);
            itemCount += artisList.size() + 1;
        }

        this.albumList = itemList.getAlbumList();
        if (albumList != null) {
            titleList.add(R.string.album);
            titleIndexList.add(itemCount);
            itemCount += albumList.size() + 1;
        }
        this.trackList = itemList.getTrackList();
        if (trackList != null) {
            titleList.add(R.string.track);
            titleIndexList.add(itemCount);
            itemCount += trackList.size() + 1;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnMoreClickLister(OnMoreClickLister onMoreClickLister) {
        this.onMoreClickLister = onMoreClickLister;
    }


    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof Integer) return HEADER_VIEW_TYPE;
        if (item instanceof Artist) return ARTIST_VIEW_TYPE;
        if (item instanceof Album) return ALBUM_VIEW_TYPE;
        if (item instanceof Track) return TRACK_VIEW_TYPE;
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case HEADER_VIEW_TYPE:
                View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
                return new HeaderViewHolder(headerView);

            case ARTIST_VIEW_TYPE:
                ArtistView itemView = new ArtistView(parent.getContext());
                return new ArtistViewHolder(itemView);

            case ALBUM_VIEW_TYPE:
                AlbumView albumView = new AlbumView(parent.getContext());
                return new AlbumViewHolder(albumView);

            case TRACK_VIEW_TYPE:
                TrackView trackView = new TrackView(parent.getContext());
                return new TrackViewHolder(trackView);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItem(position) instanceof Integer) {
            onBindHeaderViewHolder((HeaderViewHolder) holder, (Integer) getItem(position));
        } else if (getItem(position) instanceof Artist) {
            onBindArtistViewHolder((ArtistViewHolder) holder, (Artist) getItem(position));
        } else if (getItem(position) instanceof Album) {
            onBindAlbumViewHolder((AlbumViewHolder) holder, (Album) getItem(position));
        } else if (getItem(position) instanceof Track) {
            onBindTrackViewHolder((TrackViewHolder) holder, (Track) getItem(position));
        }
    }

    private void onBindHeaderViewHolder(HeaderViewHolder holder, Integer res) {
        holder.title.setText(res);
        switch (res) {
            case R.string.artist:
                holder.btnMore.setOnClickListener(view -> onMoreClickLister.onArtistMoreClicked());
                break;
            case R.string.album:
                holder.btnMore.setOnClickListener(view -> onMoreClickLister.onAlbumMoreClicked());
                break;
            case R.string.track:
                holder.btnMore.setOnClickListener(view -> onMoreClickLister.onTrackMoreClicked());
                break;

        }

    }


    private void onBindArtistViewHolder(ArtistViewHolder holder, Artist artist) {
        holder.artistView.setArtist(artist);
        if (onItemClickListener != null)
            holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(artist));

    }

    private void onBindAlbumViewHolder(AlbumViewHolder holder, Album album) {
        holder.albumView.setAlbum(album);
        if (onItemClickListener != null)
            holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(album));

    }

    private void onBindTrackViewHolder(TrackViewHolder holder, Track track) {
        holder.trackView.setTrack(track);
        if (onItemClickListener != null)
            holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(track));

    }

    public Object getItem(int position) {
        if (isItemHeader(position)) return titleList.get(titleIndexList.indexOf(position));
        int headerCount = 0;
        if (artisList != null) {
            headerCount++;
            if (artisList.size() > position - headerCount)
                return artisList.get(position - headerCount);

            position = position - artisList.size();
        }

        if (albumList != null) {
            headerCount++;
            if (albumList.size() > position - headerCount)
                return albumList.get(position - headerCount);

            position -= albumList.size();
        }
        if (trackList != null) {
            headerCount++;
            if (trackList.size() > position - headerCount)
                return trackList.get(position - headerCount);

            position -= trackList.size();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }


    public boolean isItemHeader(int position) {
        return titleIndexList.contains(position);
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {
        ArtistView artistView;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            artistView = (ArtistView) itemView;
        }
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        AlbumView albumView;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            albumView = (AlbumView) itemView;
        }
    }

    class TrackViewHolder extends RecyclerView.ViewHolder {
        TrackView trackView;

        public TrackViewHolder(View itemView) {
            super(itemView);
            trackView = (TrackView) itemView;
        }
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView title;

        @BindView(R.id.btnMore)
        Button btnMore;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Artist artist);

        void onItemClick(Track track);

        void onItemClick(Album album);
    }

    public interface OnMoreClickLister {
        void onArtistMoreClicked();

        void onAlbumMoreClicked();

        void onTrackMoreClicked();

    }
}

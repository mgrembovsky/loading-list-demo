package com.example.videolist.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.videolist.R;
import com.example.videolist.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ListDataHolder> {

    private List<ListItem> dataList = new ArrayList<>();

    @Override
    public ListDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, parent, false);
        return new ListDataHolder(view);
    }

    @Override
    public void onBindViewHolder(ListDataHolder holder, int position) {
        holder.onBind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<ListItem> listItems) {
        this.dataList = listItems;
        notifyDataSetChanged();
    }

    class ListDataHolder extends RecyclerView.ViewHolder {

        private final TextView studio;
        private final TextView title;
        private final ImageView thumbnail;

        ListDataHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.ivThumb);
            title = (TextView) view.findViewById(R.id.tvTitle);
            studio = (TextView) view.findViewById(R.id.tvStudio);
        }

        void onBind(ListItem item) {
            title.setText(item.getTitle());
            studio.setText(item.getStudio());
            Glide.with(thumbnail.getContext()).load(item.getThumbnail()).centerCrop().into(thumbnail);
        }
    }
}

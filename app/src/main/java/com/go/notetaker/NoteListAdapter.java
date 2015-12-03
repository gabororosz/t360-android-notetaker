package com.go.notetaker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gabororosz on 03/12/15.
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {

    private List<String> values;
    private int itemLayout;

    public NoteListAdapter(List<String> values, int itemLayout) {
        this.values = values;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.setTitle(this.values.get(position));
    }

    @Override public int getItemCount() {
        return values.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void setTitle(String title) {
            textView.setText(title);
        }
    }

}

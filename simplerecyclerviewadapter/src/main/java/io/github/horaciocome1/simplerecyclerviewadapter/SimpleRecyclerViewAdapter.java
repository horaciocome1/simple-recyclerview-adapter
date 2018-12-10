package io.github.horaciocome1.simplerecyclerviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static io.github.horaciocome1.simplerecyclerviewadapter.Constants.ITEM_LAYOUT_NOT_SPECIFIED;

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

    private List list;
    private int itemLayout;
    private OnBindViewHolder onBindViewHolder;
    private ViewHolder viewHolder = null;

    private SimpleRecyclerViewAdapter(List list, int itemLayout, OnBindViewHolder onBindViewHolder) {
        this.list = list;
        this.itemLayout = itemLayout;
        this.onBindViewHolder = onBindViewHolder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (!(itemLayout == ITEM_LAYOUT_NOT_SPECIFIED)) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            viewHolder = new ViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!(onBindViewHolder == null)) onBindViewHolder.onBind(holder, list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View view;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public View getViewById(int viewId) {
            return view.findViewById(viewId);
        }

    }

    public interface OnBindViewHolder {

        void onBind(@NonNull ViewHolder holder, Object object);

    }

    public static class Builder {

        private List list = null;
        private int itemLayout = ITEM_LAYOUT_NOT_SPECIFIED;
        private SimpleRecyclerViewAdapter.OnBindViewHolder onBindViewHolder = null;

        public Builder setList(List list) {
            this.list = list;
            return this;
        }

        public Builder setItemLayout(int itemLayout) {
            this.itemLayout = itemLayout;
            return this;
        }

        public Builder setOnBindViewHolder(SimpleRecyclerViewAdapter.OnBindViewHolder onBindViewHolder) {
            this.onBindViewHolder = onBindViewHolder;
            return this;
        }

        public SimpleRecyclerViewAdapter build() {
            return new SimpleRecyclerViewAdapter(list, itemLayout, onBindViewHolder);
        }

    }


}

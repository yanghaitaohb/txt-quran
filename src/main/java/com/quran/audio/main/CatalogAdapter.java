package com.quran.audio.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quran.audio.R;
import com.quran.audio.teaching.TeachingAdapter;

import java.util.List;

/**
 * author: haitao
 * create at: 2019/1/20
 */
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.MyViewHolder> {
    private final static String TAG = TeachingAdapter.class.getSimpleName();
    private List<String> list;
    private Context context;
    CatalogAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    public void setData(List<String> list) {
        this.list = list;
    }
    @Override
    public CatalogAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_catalog_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(CatalogAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}

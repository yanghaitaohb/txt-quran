package com.quran.audio.teaching;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.quran.audio.R;
import com.quran.audio.message.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class TeachingAdapter extends RecyclerView.Adapter<TeachingAdapter.MyViewHolder> {
    private final static String TAG = TeachingAdapter.class.getSimpleName();
    private List<String> list;
    private Context context;

    TeachingAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    public void setData(List<String> list) {
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teaching_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:  ==== 播放 " + holder.getAdapterPosition());
                EventBus.getDefault().post(new MessageEvent(MessageEvent.PLAY_POSITION, holder.getAdapterPosition()));
            }
        });
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

package com.quran.audio.main;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.quran.audio.R;
import com.quran.audio.base.control.ControlPresenter;
import com.quran.audio.message.MessageEvent;
import com.quran.audio.utils.FileUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends ControlPresenter<IMain> implements View.OnClickListener{
    private final static int WHAT_REFRESH_DATA = 1;
    private List<String> recyclerData;
    private CatalogAdapter adapter;
    public MainPresenter(IMain mView) {
        super(mView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initHandler();
        initAdapter();
        EventBus.getDefault().register(this);
    }

    private void initAdapter() {
        recyclerData = new ArrayList<>();
        adapter = new CatalogAdapter(getContext(), recyclerData);
        mView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ===========main click====");
        switch (v.getId()) {
        }
    }
    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_REFRESH_DATA:
                adapter.setData(recyclerData);
                adapter.notifyDataSetChanged();
                break;
        }
    }
    private void getVolumeData() {
        recyclerData = new ArrayList<>(31);
        for (int i = 1; i < 31; i++) {
            recyclerData.add("第" + i + "卷");
        }
        getHandler().sendEmptyMessage(WHAT_REFRESH_DATA);
    }
    private void getChapterData() {
        recyclerData = new ArrayList<>(115);
        new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerData = FileUtils.getFileList(getContext(), "chapter.txt");
                getHandler().sendEmptyMessage(WHAT_REFRESH_DATA);
            }
        }).start();
    }
    private void getLessonData() {
        recyclerData = new ArrayList<>(6237);
        for (int i = 1; i < 6237; i++) {
            recyclerData.add("第" + i + "节");
        }
        getHandler().sendEmptyMessage(WHAT_REFRESH_DATA);
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void refreshData(MessageEvent event) {
        Log.d(TAG, "refreshData:  ==  " + event.getAction());
        switch (event.getAction()) {
            case MessageEvent.VOLUME:
                getVolumeData();
                break;
            case MessageEvent.CHAPTER:
                getChapterData();
                break;
            case MessageEvent.LESSON:
                getLessonData();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

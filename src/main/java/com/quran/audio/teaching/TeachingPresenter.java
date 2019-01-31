package com.quran.audio.teaching;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

public class TeachingPresenter extends ControlPresenter<ITeaching> implements View.OnClickListener,Spinner.OnItemSelectedListener{

    private final static int WHAT_DISMISS_DIALOG = 1;
    private List<String> recyclerData;
    private TeachingAdapter teachingAdapter;

    TeachingPresenter(ITeaching mView) {
        super(mView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initHandler();
        initAdapter();
    }
    private void initAdapter() {
        recyclerData = new ArrayList<>();
        teachingAdapter = new TeachingAdapter(getContext(), recyclerData);
        mView.setRecyclerAdapter(teachingAdapter);
        showProgressDialog(false, "加载中。。。");
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(800);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                recyclerData = FileUtils.getFileList(getContext(), "text.txt");
                getHandler().sendEmptyMessage(WHAT_DISMISS_DIALOG);
            }
        }).start();
    }

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_DISMISS_DIALOG:
                teachingAdapter.setData(recyclerData);
                teachingAdapter.notifyDataSetChanged();
                dismissProgressDialog();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected:  ============ ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ======teach click======");
        switch (v.getId()) {
            case R.id.tv_lesson:
                EventBus.getDefault().post(new MessageEvent(MessageEvent.LESSON));
                break;
            case R.id.tv_chapter:
                EventBus.getDefault().post(new MessageEvent(MessageEvent.CHAPTER));
                break;
            case R.id.tv_volume:
                EventBus.getDefault().post(new MessageEvent(MessageEvent.VOLUME));
                break;
        }
    }
}

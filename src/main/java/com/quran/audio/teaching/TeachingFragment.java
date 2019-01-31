package com.quran.audio.teaching;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.quran.audio.R;
import com.quran.audio.base.control.ControlActivity;
import com.quran.audio.base.control.ControlFragment;

public class TeachingFragment  extends ControlFragment<TeachingPresenter> implements ITeaching {
    private RecyclerView recyclerView;

    private TextView tvContinue, tvBookmark, tvCollection;
    private TextView tvLesson, tvChapter, tvVolume;

    @Override
    protected void setOnListener() {
        tvLesson.setOnClickListener(mPresenter);
        tvChapter.setOnClickListener(mPresenter);
        tvVolume.setOnClickListener(mPresenter);
    }

    @Override
    public int getContentViewResource() {
        return R.layout.fragment_teaching;
    }

    @Override
    protected void onViewInit(View contentView) {
        super.onViewInit(contentView);

        tvLesson = (TextView) findViewById(R.id.tv_lesson);
        tvChapter = (TextView) findViewById(R.id.tv_chapter);
        tvVolume = (TextView) findViewById(R.id.tv_volume);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public TeachingPresenter createPresenter() {
        return new TeachingPresenter(this);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void setRecyclerAdapter(TeachingAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}

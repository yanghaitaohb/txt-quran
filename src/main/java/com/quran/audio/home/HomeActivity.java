package com.quran.audio.home;

import android.content.Context;
import android.widget.TextView;

import com.quran.audio.R;
import com.quran.audio.base.control.ControlActivity;

public class HomeActivity extends ControlActivity<HomePresenter> implements IHome {
    private TextView tvChant, tvRead, tvWorship, tvTeach, tvCollection, tvBookMake;
    @Override
    protected int getContentViewResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void onViewInit() {
        super.onViewInit();
        tvChant = (TextView) findViewById(R.id.tv_chant);
        tvRead = (TextView) findViewById(R.id.tv_read);
        tvTeach = (TextView) findViewById(R.id.tv_teach);
        tvWorship = (TextView) findViewById(R.id.tv_worship);
        tvBookMake = (TextView) findViewById(R.id.tv_bookmark);
        tvCollection = (TextView) findViewById(R.id.tv_collection);
    }

    @Override
    protected void setOnListener() {
        super.setOnListener();
        tvChant.setOnClickListener(mPresenter);
        tvTeach.setOnClickListener(mPresenter);
        tvRead.setOnClickListener(mPresenter);
        tvWorship.setOnClickListener(mPresenter);
        tvBookMake.setOnClickListener(mPresenter);
        tvCollection.setOnClickListener(mPresenter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }
}

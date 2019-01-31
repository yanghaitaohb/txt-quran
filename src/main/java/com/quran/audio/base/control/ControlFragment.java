package com.quran.audio.base.control;

import android.os.Bundle;

import com.quran.audio.base.BaseFragment;

/**
 * author: haitao
 * create at: 2019/1/19
 */
public abstract class ControlFragment <T extends ControlPresenter> extends BaseFragment {

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        mPresenter.onCreate(savedInstanceState);
    }


    @Override
    protected void onViewCreateBefore() {
        mPresenter.onViewCreateBefore();
    }

    @Override
    protected void onViewCreated() {
        mPresenter.onViewCreated();
    }

    public abstract T createPresenter();

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

}

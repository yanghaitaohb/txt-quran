package com.quran.audio.chant;

import com.quran.audio.R;
import com.quran.audio.base.control.ControlFragment;

/**
 * author: haitao
 * create at: 2019/1/19
 */
public class ChantFragment extends ControlFragment<ChantPresenter> implements IChant{
    @Override
    public ChantPresenter createPresenter() {
        return new ChantPresenter(this);
    }

    @Override
    public int getContentViewResource() {
        return R.layout.fragment_chant;
    }
}

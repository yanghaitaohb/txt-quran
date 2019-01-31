package com.quran.audio.read;

import com.quran.audio.R;
import com.quran.audio.base.control.ControlFragment;

/**
 * author: haitao
 * create at: 2019/1/19
 */
public class ReadFragment extends ControlFragment<ReadPresenter> implements IRead {
    @Override
    public ReadPresenter createPresenter() {
        return new ReadPresenter(this);
    }

    @Override
    public int getContentViewResource() {
        return R.layout.fragment_read;
    }
}

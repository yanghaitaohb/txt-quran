package com.quran.audio.home;

import android.content.Intent;
import android.view.View;

import com.quran.audio.MyApplication;
import com.quran.audio.R;
import com.quran.audio.base.control.ControlPresenter;
import com.quran.audio.main.MainActivity;

public class HomePresenter extends ControlPresenter<HomeActivity> implements View.OnClickListener {
    public HomePresenter(HomeActivity mView) {
        super(mView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_chant:
                startMain(MyApplication.CHANT_INDEX);
                break;
            case R.id.tv_teach:
                startMain(MyApplication.TEACH_INDEX);
                break;
            case R.id.tv_read:
                startMain(MyApplication.READ_INDEX);
                break;
            case R.id.tv_worship:
                startMain(MyApplication.WORSHIP_INDEX);
                break;
        }
    }
    private void startMain(int index) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
        mView.finish();
    }
}

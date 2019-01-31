package com.quran.audio;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.quran.audio.customView.NumberProgressBar;

public class MyApplication extends Application {

    NumberProgressBar progressBar;

    public final static int CHANT_INDEX = 1;
    public final static int TEACH_INDEX = 2;
    public final static int READ_INDEX = 3;
    public final static int WORSHIP_INDEX = 4;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, AudioService.class);
        startService(intent);
    }
    /**
     * 获取的app的Application对象
     *
     * @param context 上下文
     * @return Application对象
     */
    public static MyApplication from(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    /**
     * 绑定时进度条View
     */
    public void bindProgressView(NumberProgressBar progressBar) {

        if (progressBar != null) {
            int view = View.GONE;
            if (this.progressBar != null) {
                int po = this.progressBar.getProgress();
                if (po > 0) {
                    progressBar.setProgress(po);
                    if (this.progressBar != null) {
                        view = this.progressBar.getVisibility();
                    }
                }
            }

            progressBar.setVisibility(view);
            this.progressBar = progressBar;
        }

    }

}

package com.quran.audio.teaching;

import android.widget.ArrayAdapter;

import com.quran.audio.base.UiView;

public interface ITeaching extends UiView {
    void setRecyclerAdapter(TeachingAdapter adapter);
}

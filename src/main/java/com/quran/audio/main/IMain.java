package com.quran.audio.main;

import android.widget.ArrayAdapter;

import com.quran.audio.base.UiView;

public interface IMain extends UiView {
    void setAdapter(CatalogAdapter adapter);
}

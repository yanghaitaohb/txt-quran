package com.quran.audio.worship;

import com.quran.audio.base.UiView;
import com.quran.audio.bean.IslamicDateBean;

import java.util.List;

public interface IWorship extends UiView {
    void setData(List<IslamicDateBean> list, List<String> signDates);
    void monthChange(int change, List<IslamicDateBean> list);
    void setSignDates(List<String> list);
    void setSignEnable(boolean enable);
}

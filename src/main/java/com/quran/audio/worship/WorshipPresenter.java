package com.quran.audio.worship;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.quran.audio.R;
import com.quran.audio.base.control.ControlPresenter;
import com.quran.audio.bean.IslamicDateBean;
import com.quran.audio.customView.CustomCalendar;
import com.quran.audio.database.DBOpenHelper;
import com.quran.audio.database.dao.SignDao;
import com.quran.audio.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WorshipPresenter extends ControlPresenter<IWorship> implements View.OnClickListener, CustomCalendar.onCalendarClickListener {

    public SimpleDateFormat dateFormat, dateFormat1;
    SignDao signDao;
    Calendar calendar = Calendar.getInstance();
    public WorshipPresenter(IWorship mView) {
        super(mView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sign:
                sign();
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        signDao = new SignDao(new DBOpenHelper(getContext()));
        mView.setData(getData(0), signDao.queryAll());
        dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        dateFormat1 = new SimpleDateFormat(
                "yyyy年MM月d日", Locale.getDefault());
    }

    private void sign() {
        signDao.insert(dateFormat.format(new Date()));
        mView.setSignDates(signDao.queryAll());
    }
    public List<IslamicDateBean> getData(int change) {

        final List<IslamicDateBean> list = new ArrayList<>(32);
        calendar.add(Calendar.MONTH, change);
        Calendar islamicCalendar;
        String islamicStr;
        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DATE); i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            islamicCalendar = TimeUtils.GregorianToIslamicCalendar(calendar);
            islamicStr = (islamicCalendar.get(Calendar.MONTH) + 1) + "/" + islamicCalendar.get(Calendar.DAY_OF_MONTH);
            list.add(new IslamicDateBean(i, islamicStr));
//            Log.d(TAG, "setData:  ===  " + i +"   " + islamicStr);
        }
        return list;
    }

    @Override
    public void onLeftRowClick() {
        Log.d(TAG, "onLeftRowClick: 点击减箭头");
        mView.monthChange(-1, getData(-1));
    }

    @Override
    public void onRightRowClick() {
        mView.monthChange(1, getData(1));
    }

    @Override
    public void onTitleClick(String monthStr, Date month) {

    }

    @Override
    public void onWeekClick(int weekIndex, String weekStr) {

    }

    @Override
    public void onDayClick(int day, String dayStr, IslamicDateBean islamicDate) {
        mView.setSignEnable(dayStr.equals(dateFormat1.format(new Date())));
    }
}

package com.quran.audio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        try {
//            String str = "621-7-14";
            String str = "2018-7-22";
            System.out.println(sdf.format(sdf.parse(str)));
            System.out.println(sdf.parse(str).getTime());

            c.setTime(sdf.parse(str));
//            c.setTime(new Date());

            System.out.println(c.get(Calendar.WEEK_OF_YEAR));
            System.out.println(c.get(Calendar.WEEK_OF_MONTH));
            System.out.println(c.get(Calendar.DAY_OF_WEEK));
            System.out.println(c.get(Calendar.DAY_OF_MONTH));
            System.out.println(c.get(Calendar.MONTH));
            System.out.println(c.getMaximum(Calendar.DATE)); //永远是31
            System.out.println(c.getActualMaximum(Calendar.DATE)); //当月实际天数
            System.out.println(c.getMaximum(Calendar.DAY_OF_YEAR));
            System.out.println(c.getActualMaximum(Calendar.DAY_OF_YEAR)); //当月实际天数

            long cha = sdf.parse("2019-1-18").getTime() - sdf.parse("622-7-16").getTime();
            long day = cha / 1000 / 60 / 60 / 24;
            System.out.println("  day =  " + day + "   " + (day / 10631.0) + "    余  " + (day % 10631)); //永远是31

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(GregorianToIslamicCalendar(c).getTime()));
        System.out.println(sdf.format(c.getTime()));
        System.out.println(c.get(Calendar.DATE));
    }

    public static double getAngle(double longitude, double latitude) {
        double MECCA_LONGITUDE = 39.816, MECCA_LATITUDE = 21.433;
        double angle = Math.toDegrees(Math.atan2(MECCA_LATITUDE - latitude, MECCA_LONGITUDE - longitude)); //纬度差，经度差
        if (angle <= 90) {
            angle = 90 - angle;
        } else {
            angle = 360 - (angle - 90);
        }
        return angle;
    }

    private static Calendar calendar = Calendar.getInstance();


    /**
     * 公历转伊斯兰日历(伊朗阴历)
     *
     * @param nowCalendar 公历
     * @return 伊斯兰日历
     */
    public static Calendar GregorianToIslamicCalendar(Calendar nowCalendar) {
        int day = 1, month = 1, year = 1411, doy = 0, tmp = 0, i = 0;
        int leap[] = {354, 355, 354, 354, 355, 354, 355, 354, 354, 355,
                354, 354, 355, 354, 354, 355, 354, 355, 354, 354,
                355, 354, 354, 355, 354, 355, 354, 354, 355, 354};
        doy = getPastDay(nowCalendar);
        if (doy < 0) {
            if (doy / 10631 != 0) {     // it is more than 30 years
                year += 30 * (doy / 10631);
                doy -= 10631 * (doy / 10631);
            }
            for (i = 29; doy < 1; i--) {
                doy += leap[i];
            }
            i = 29 - i;
            year -= i;
            if (doy != 354 && doy != 355) {
                month = (doy / 59) * 2 + ((doy % 59) / 30);
                day = doy - ((month / 2) * 59 + (month % 2) * 30);
            } else {
                year++;
                month = 0;  //1;
                day = 0;
            }
        } else {
            if (doy / 10631 != 0) {     // 30年
                year += 30 * (doy / 10631);
                doy -= 10631 * (doy / 10631);
            }
            for (i = 0; doy > -1; i++) {
                tmp = doy;
                doy -= leap[i];
            }
            i--;
            year += i;
            doy = tmp;
            if (doy != 354) {
                month = (doy / 59) * 2 + ((doy % 59) / 30);
                day = doy - ((month / 2) * 59 + (month % 2) * 30);
            } else {
                month = 11;  //12;
                day = 29;
            }
        }
        day += 1;

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        return calendar;
    }

    /**
     * 从1990年7月24日至传入时间过去多少天  1990-7-24为伊历1411-1-1
     *
     * @param nCalendar
     * @return
     */
    private static int getPastDay(Calendar nCalendar) {
        int pastDay = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 24);
        if (calendar.get(Calendar.YEAR) == nCalendar.get(Calendar.YEAR)) { //同一年
            return nCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
        }
        if (calendar.get(Calendar.YEAR) > nCalendar.get(Calendar.YEAR)) { //1990年前
            pastDay -= calendar.get(Calendar.DAY_OF_YEAR);
            int year = calendar.get(Calendar.YEAR);
            for (int i = year - 1; i > nCalendar.get(Calendar.YEAR); i--) {
                calendar.set(Calendar.YEAR, i);
                pastDay -= calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            pastDay = pastDay - (nCalendar.getActualMaximum(Calendar.DAY_OF_YEAR) - nCalendar.get(Calendar.DAY_OF_YEAR));
            return pastDay;
        }
        pastDay = calendar.getActualMaximum(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);

        int nYear = nCalendar.get(Calendar.YEAR);
        for (int i = 1991; i < nYear; i++) {
            calendar.set(Calendar.YEAR, i);
            pastDay += calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        }
        pastDay += nCalendar.get(Calendar.DAY_OF_YEAR);
        return pastDay;
    }
}

//*****************废弃方法****************
//    /**
//     * 公历转伊斯兰日历(伊朗阴历)
//     *
//     * @param nowCalendar 公历
//     * @return 伊斯兰日历
//     */
//    public static Calendar GregorianToIslamicCalendar(Calendar nowCalendar) {
//
//        int mYear = nowCalendar.get(Calendar.YEAR);
//        int mMon = nowCalendar.get(Calendar.MONTH) + 1;
//        int mDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
//        int day = 24, month = 9, year = 1420, doy = 0, tmp = 0, i = 0;
//        int leap[] = {354, 355, 354, 354, 355, 354, 355, 354, 354, 355,
//                354, 354, 355, 354, 354, 355, 354, 355, 354, 354,
//                355, 354, 354, 355, 354, 355, 354, 354, 355, 354};
//        doy = getTotalDayYin(mYear, mMon, mDay);   //get the totle day from 2000/01/01 to chrisClock  it is the same as 1420/09/24
//        System.out.println("doy == " + doy);
//        doy -= 97;                //trans it to 1421/01/01   from 1420/09/24
//        if (doy < 0) {    //take care of the 1420 year
//            doy += 97;
//            if (doy < 8)
//                day += doy - 1;
//            else if (doy < 37) {
//                month += 1;
//                day = (doy - 7);
//            } else if (doy < 67) {
//                month += 2;
//                day = (doy - 36);
//            } else {
//                month += 3;
//                day = doy - 66;
//            }
//        } else {                //take care the others
//            if (doy / 10631 != 0) {     // it is more than 30 years
//                year += 30 * (doy / 10631);
//                doy -= 10631 * (doy / 10631);
//            }
//            for (i = 0; doy > -1; i++) {
//                tmp = doy;
//                doy -= leap[i];
//            }
//            year += i;                  //year is ok
//            doy = tmp;
//
//            System.out.println("doy2 == " + doy);
//            if (doy != 354) {
//                month = (doy / 59) * 2 + ((doy % 59) / 30);
//                day = doy - ((month / 2) * 59 + (month % 2) * 30);
//            } else   //it is leap year
//            {
//                month = 11;  //12;
//                day = 29;
//            }
//            month += 1;
//            day += 1;
//        }
//        mYear = year;
//        mMon = month - 1;
//        mDay = day;
//
//        calendar.set(Calendar.YEAR, mYear);
//        calendar.set(Calendar.MONTH, mMon);
//        calendar.set(Calendar.DATE, mDay);
//        return calendar;
//    }

//    private static int getTotalDayYin(int nYear, int nMonth, int nDay) {
//        int yDay = 0;
//        int yearDay = 0;
//        int DaysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//
//        int nCounter;
//        DaysInMonth[1] = isLeapYear(nYear) ? 29 : 28;
//        for (nCounter = 0; nCounter < nMonth - 1; ++nCounter) {
//            yDay += DaysInMonth[nCounter];
//        }
//        if (nYear % 4 != 0)
//            yearDay = (nYear - 2000) * 365 + (nYear - 2000) / 4 + 1;
//        else
//            yearDay = (nYear - 2000) * 365 + (nYear - 2000) / 4;
//
//        return (yearDay + yDay + nDay);
//    }
//
//    //charge is or not Leap Year
//    private static boolean isLeapYear(int nYear) {
//        return (nYear % 4 == 0) && (nYear % 100 != 0) || (nYear % 400 == 0);
//    }


package ww.com.detailcharge.utils.tools;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public class CaladarUtils {
    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;
    private static String mHms;

    public static String StringData(String flag) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        String h = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String m = String.valueOf(c.get(Calendar.MINUTE));
        String s = String.valueOf(c.get(Calendar.SECOND));
        mHms = h + ":" + m + ":" + s;
        if ("1".equals(mWay)) {
            mWay = "星期天";
        } else if ("2".equals(mWay)) {
            mWay = "星期一";
        } else if ("3".equals(mWay)) {
            mWay = "星期二";
        } else if ("4".equals(mWay)) {
            mWay = "星期三";
        } else if ("5".equals(mWay)) {
            mWay = "星期四";
        } else if ("6".equals(mWay)) {
            mWay = "星期五";
        } else if ("7".equals(mWay)) {
            mWay = "星期六";
        }

        switch (flag) {
            case "YEAR":
                return mYear;     //年
            case "MONTH":
                return mMonth;    //月
            case "DAY":
                return mDay;      //日
            case "WEEK":
                return mWay;      //星期
            case "HMS":
                return mHms;      //时分秒
            case "HH":
                return h;
            default:
                return mYear + "年" + mMonth + "月" + mDay + "日" + "/" + mWay;
        }

    }

}

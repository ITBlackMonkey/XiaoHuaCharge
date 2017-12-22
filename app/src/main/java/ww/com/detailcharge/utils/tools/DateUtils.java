package ww.com.detailcharge.utils.tools;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public class DateUtils {

    static long time = System.currentTimeMillis();
    static Date date = new Date(time);
    static SimpleDateFormat format;

    public static String getYMDHMSE() {
        format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE");
        Log.e("time", "time1=" + format.format(date));
        return format.format(date);
    }


    public static String getYMDHMS() {
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Log.e("time", "time2=" + format.format(date));
        return format.format(date);
    }

    public static String getYMD() {
        format = new SimpleDateFormat("yyyy/MM/dd");
        Log.e("time", "time3=" + format.format(date));
        return format.format(date);
    }

    public static String getHMS() {
        format = new SimpleDateFormat("HH:mm:ss");
        Log.e("time", "time4=" + format.format(date));
        return format.format(date);
    }


    public static String getWeek() {
        format = new SimpleDateFormat("EEEE");
        Log.e("time", "time5=" + format.format(date));
        return format.format(date);
    }

    public static String getE() {
        format = new SimpleDateFormat("E");
        Log.e("time", "time6=" + format.format(date));
        return format.format(date);
    }

}

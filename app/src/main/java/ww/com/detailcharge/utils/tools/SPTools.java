package ww.com.detailcharge.utils.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author: WANGWEI on 2017/12/5 0005.
 */

public class SPTools {

    public static void spPutString(Context context, String key, String value) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor edit = userInfo.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void spPutInt(Context context, String key, int value) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor edit = userInfo.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static void spPutBoolean(Context context, String key, boolean value) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor edit = userInfo.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static String spGetString(Context context, String key) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", 0);
        return userInfo.getString(key, "");
    }

    public static int spGetInt(Context context, String key) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", 0);
        return userInfo.getInt(key, 0);
    }


    public static boolean spGetBoolean(Context context, String key) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", 0);
        return userInfo.getBoolean(key, false);
    }

    public static void spClear(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.clear();
        editor.commit();
    }
}

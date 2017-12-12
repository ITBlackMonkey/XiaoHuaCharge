package ww.com.detailcharge.utils.threadutil;

import android.os.Handler;
import android.os.Looper;

/**
 * @author: WANGWEI on 2017/12/4 0004.
 */

public class ThreadUtils {

    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {     //如果是主线程
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }

    public static void runOnSubthread(Runnable runnable) {

        new Thread(runnable).start();
    }
}

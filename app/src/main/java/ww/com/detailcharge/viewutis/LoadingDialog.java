package ww.com.detailcharge.viewutis;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import ww.com.detailcharge.R;

/**
 * @author: WANGWEI on 2017/12/7 0007.
 */

public class LoadingDialog extends AppCompatDialog {
    private static LoadingDialog mLoadingProgress;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public static void showProgress(Context context, CharSequence message) {
        mLoadingProgress = new LoadingDialog(context, R.style.loading_dialog);//自定义style文件主要让北京变成透明并去掉标题部分<!-- 自定义loading dialog -->
        mLoadingProgress.setCanceledOnTouchOutside(false);
        mLoadingProgress.setTitle("");
        mLoadingProgress.setContentView(R.layout.loading_layout);
        if (message == null || TextUtils.isEmpty(message)) {
            mLoadingProgress.findViewById(R.id.loading_tv).setVisibility(View.GONE);
        } else {
            TextView tv = (TextView) mLoadingProgress.findViewById(R.id.loading_tv);
            tv.setText(message);
        }

        mLoadingProgress.setCancelable(false);
        mLoadingProgress.show();
    }


    public static void dismissprogress(){
        if(mLoadingProgress!=null){

            mLoadingProgress.dismiss();
        }
    }

}

package ww.com.detailcharge.fragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: WANGWEI on 2017/12/11 0011.
 */

public class BaseFragment extends FragmentActivity {
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}

package ww.com.detailcharge.viewutis;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * @author: WANGWEI on 2017/12/19 0019.
 */

public class MySrollView extends ScrollView {

    private ScrollViewListener scrollViewListener = null;
    public MySrollView(Context context) {
        this(context,null);
    }

    public MySrollView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MySrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldl, oldt);
        }
    }
}

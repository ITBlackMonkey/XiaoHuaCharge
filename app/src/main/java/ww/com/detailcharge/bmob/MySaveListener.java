package ww.com.detailcharge.bmob;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public abstract class MySaveListener extends SaveListener<String> {
    @Override
    public void done(String string, BmobException e) {
        if (e == null) {
            succ();
        } else {
            faill(e.getMessage());
        }
    }

    public abstract void succ();

    public abstract void faill(String errMsg);
}

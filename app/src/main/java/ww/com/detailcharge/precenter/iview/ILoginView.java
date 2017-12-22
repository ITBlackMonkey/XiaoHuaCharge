package ww.com.detailcharge.precenter.iview;

/**
 * @author: WANGWEI on 2017/12/5 0005.
 */

public interface ILoginView {
    public void nameEmpty();
    public void passwordEmpty();
    public void phoneEmpty();
    public void nameNotExsit();
    public void passwordErr();
    public void phoneErr();
    public void loginErr(String errMsg);
    public void loginSucc();
    void saveSucc();
    void saveFaill(String errMsg);
}

package ww.com.detailcharge.precenter.iview;

/**
 * @author: WANGWEI on 2017/12/4 0004.
 */

public interface IRegisterView {

    public abstract void usernameEmpty();

    public abstract void passwordEmpty();

    public abstract void phoneEmpty();

    public abstract void registerSucc();

    public abstract void nameExist();

    public abstract void registerFail(String msg);
}

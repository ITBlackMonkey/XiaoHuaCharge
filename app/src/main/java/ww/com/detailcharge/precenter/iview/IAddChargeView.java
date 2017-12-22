package ww.com.detailcharge.precenter.iview;

import ww.com.detailcharge.db.AddCharge;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public interface IAddChargeView {
    void savaSucc(AddCharge addCharge);
    void saveFaill(String errMsg);
}

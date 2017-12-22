package ww.com.detailcharge.precenter.iview;

import java.util.List;

import ww.com.detailcharge.db.AddCharge;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public interface IGetChargeView {
    void findNoData();
    void findSucc(List<AddCharge> list);
    void findFaill(String errMsg);
}

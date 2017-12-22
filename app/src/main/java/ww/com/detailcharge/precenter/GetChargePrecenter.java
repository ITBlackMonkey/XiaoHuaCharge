package ww.com.detailcharge.precenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import ww.com.detailcharge.MyApplication;
import ww.com.detailcharge.db.AddCharge;
import ww.com.detailcharge.precenter.iview.IGetChargeView;
import ww.com.detailcharge.utils.threadutil.ThreadUtils;
import ww.com.detailcharge.utils.tools.CaladarUtils;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public class GetChargePrecenter {
    IGetChargeView getChargeView;

    public GetChargePrecenter(IGetChargeView getChargeView) {
        this.getChargeView = getChargeView;
    }

    public void getDataFromServer() {
        ThreadUtils.runOnSubthread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<AddCharge> query = new BmobQuery<>();
                query.order("-day");
                query.addWhereEqualTo("month", CaladarUtils.StringData("MONTH"));
                query.addWhereEqualTo("id", MyApplication.getInstance().getUserinfo().getId());
                query.findObjects(new FindListener<AddCharge>() {
                    @Override
                    public void done(List<AddCharge> list, BmobException e) {
                        if (e == null) {
                            if (list.size() == 0) {
                                getChargeView.findNoData();
                            } else {
                                getChargeView.findSucc(list);
                            }
                        } else {
                            getChargeView.findFaill(e.getMessage());
                        }
                    }
                });
            }

        });
    }

}

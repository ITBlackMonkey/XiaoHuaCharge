package ww.com.detailcharge.precenter;

import ww.com.detailcharge.bmob.MySaveListener;
import ww.com.detailcharge.db.AddCharge;
import ww.com.detailcharge.precenter.iview.IAddChargeView;
import ww.com.detailcharge.utils.threadutil.ThreadUtils;
import ww.com.detailcharge.utils.tools.CaladarUtils;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public class AddChargePrecenter {
    IAddChargeView iAddChargeView;
    boolean        aBoolean;

    public AddChargePrecenter(IAddChargeView addChargeView) {
        this.iAddChargeView = addChargeView;
    }

    public void addCharge(final int userId, final int type, final int imgDrawable, final String text, final String moneyText, final String describe, final boolean aBoolean) {
        ThreadUtils.runOnSubthread(new Runnable() {
            @Override
            public void run() {
                final AddCharge addCharge = new AddCharge();
                addCharge.setId(userId);
                addCharge.setType(type);
                addCharge.setImageDrawable(imgDrawable);
                addCharge.setText(text);
                addCharge.setMoneyText(moneyText);
                addCharge.setDescribe(describe);
                addCharge.setYear(CaladarUtils.StringData("YEAR"));
                addCharge.setMonth(CaladarUtils.StringData("MONTH"));
                addCharge.setDay(CaladarUtils.StringData("DAY"));
                addCharge.setWeek(CaladarUtils.StringData("WEEK"));
                addCharge.setHms(CaladarUtils.StringData("HMS"));
                if (aBoolean) {
                    addCharge.setTitleVisibility(true);
                } else {
                    addCharge.setTitleVisibility(false);
                }
                addCharge.save(new MySaveListener() {
                    @Override
                    public void succ() {
                        iAddChargeView.savaSucc(addCharge);
                    }

                    @Override
                    public void faill(String errMsg) {
                        iAddChargeView.saveFaill(errMsg);
                    }
                });
            }
        });

    }


}
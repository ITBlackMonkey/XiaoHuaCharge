package ww.com.detailcharge.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: WANGWEI on 2017/12/6 0006.
 */

public class FragmentFactory {

    static Map<Integer,Fragment> fragmentMap = new HashMap<>();
    public static Fragment getFrament(int key){
        if (fragmentMap.get(key) == null) {
            switch (key) {
                case 0:
                    fragmentMap.put(key,new HomeFragment());
                break;
                case 1:
                    fragmentMap.put(key,new PayBookFragment());
                    break;
                case 2:
                    fragmentMap.put(key,new WriteChargeFragment());
                    break;
                case 3:
                    fragmentMap.put(key,new PersonalInfoFragment());
                    break;
                case 4:
                    fragmentMap.put(key,new AboutUsFragment());
                    break;
                case 5:
                    fragmentMap.put(key,new ExpenseFragment());
                    break;
                case 6:
                    fragmentMap.put(key,new IncomeFragment());
                    break;

                default:
                    break;
            }
        }
         return fragmentMap.get(key);
    }


}

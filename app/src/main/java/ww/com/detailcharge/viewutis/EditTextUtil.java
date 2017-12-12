package ww.com.detailcharge.viewutis;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author: WANGWEI on 2017/12/4 0004.
 */

public class EditTextUtil {
    public static void hiddenHint(EditText editText, final TextInputLayout textInputLayout, final String hint){
       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               textInputLayout.setHint(hint);
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

    }
}

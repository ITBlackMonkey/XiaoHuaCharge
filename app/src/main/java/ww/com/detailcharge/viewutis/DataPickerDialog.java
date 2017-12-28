package ww.com.detailcharge.viewutis;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import ww.com.detailcharge.R;

/**
 * @author: WANGWEI on 2017/12/26 0026.
 */

public class DataPickerDialog  extends Dialog{


    public DataPickerDialog(@NonNull Context context) {
        this(context,-1);
    }

    public DataPickerDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public void showDialog(final Context context) {
        DataPickerDialog pickerDialog = new DataPickerDialog(context, R.style.my_dialog);
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_datepicker, null);
        pickerDialog.setContentView(view);

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(context,year+"-"+(month+1)+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(context, DatePickerDialog.THEME_HOLO_LIGHT, dateSetListener, 1949, 10, 1);

        Window window = pickerDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialogstyle);
        WindowManager.LayoutParams lp = window.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;// 宽度
        view.measure(0, 0);
        lp.height = view.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        window.setAttributes(lp);
        dialog.show();
        pickerDialog.show();
    }


}

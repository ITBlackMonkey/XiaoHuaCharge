package ww.com.detailcharge.db;

import cn.bmob.v3.BmobObject;

/**
 * @author: WANGWEI on 2017/12/20 0020.
 */

public class AddCharge extends BmobObject {
    int    id; //UserID
    int    type; //账目类型，支出/收入
    int    imageDrawable; //图片路径
    String text; // 文字描述
    String moneyText;
    String describe; //描述
    String year; //年
    String month; //月
    String day; // 日
    String week ; // 星期
    String hms; // 时分秒
    boolean titleVisibility;
    public String getMoneyText() {
        return moneyText;
    }

    public void setMoneyText(String moneyText) {
        this.moneyText = moneyText;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getHms() {
        return hms;
    }

    public void setHms(String hms) {
        this.hms = hms;
    }

    public boolean isTitleVisibility() {
        return titleVisibility;
    }

    public void setTitleVisibility(boolean titleVisibility) {
        this.titleVisibility = titleVisibility;
    }
}

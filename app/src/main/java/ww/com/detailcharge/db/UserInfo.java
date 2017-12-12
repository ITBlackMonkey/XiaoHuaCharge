package ww.com.detailcharge.db;

import cn.bmob.v3.BmobObject;

/**
 * @author: WANGWEI on 2017/12/1 0001.
 */

public class UserInfo extends BmobObject {


    private String username;      // 用户名
    private String password;       // 密码
    private int    type;              // 用户类型
    private String describe;         //描述
    private int    userimg;          //头像
    private String phoneNumber;   // 手机号
    private String email;           //电子邮箱

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getUserimg() {
        return userimg;
    }

    public void setUserimg(int userimg) {
        this.userimg = userimg;
    }
}

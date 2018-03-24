package com.cheese.radio.ui.user.enroll;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/24.
 */

public class EnroolParams extends IkeParams {
//    method	方法名	是	固定	createOrder
//    token	用户令牌	是	用户登录或注册后获取
//    name	宝宝姓名	是	学员的姓名
//    sex	宝宝性别	是
//    birthday	宝宝生日	是
//    phone	联系电话	是
//    address	家庭住址	是	浙江省,宁波市,鄞州区
//    ageRange	年龄段	是	固定	4-5 ｜6-7
//    productId	套餐Id	是	套餐列表接口获取
//    payType	支付方式	是	用户选择	zfb｜weixin｜bank
    private String method;
    private String name;
    private String sex;
    private String birthday;
    private String phone;
    private String address;
    private String ageRange;
    private int productId;
    private String payType;
    public EnroolParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}

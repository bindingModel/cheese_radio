package com.cheese.radio.ui.user.enroll.params;

import android.text.TextUtils;
import android.view.View;

import com.binding.model.util.BaseUtil;
import com.cheese.radio.base.IkeParams;
import com.cheese.radio.util.MyBaseUtil;


import static com.binding.model.util.BaseUtil.getPhoneError;
import static com.binding.model.util.BaseUtil.isValidToast;

public class CreateOrderParams extends IkeParams {
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
    //fiekdId 场地
    private String method;
    private String name;
    private String sex;
    private String birthday;
    private String phone;
    private String address;
    private String ageRange;
    private int productId;
    private String payType;
    private int fieldId;
    private String instructor;
    private String parentName;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public CreateOrderParams(String method) {
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

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setPayType(Integer payType) {
        if (payType == 0) this.payType = "weixin";
        if (payType == 1) this.payType = "zfb";
    }

    public boolean isLeagal(View view) {

        if (!isValidToast(view, MyBaseUtil.getNameError(name))) return false;
        if (TextUtils.isEmpty(sex)) {
            BaseUtil.toast("性别还未选取");
            return false;
        }
        if (TextUtils.isEmpty(birthday)) {
            BaseUtil.toast("生日还未选取");
            return false;
        }
        if (!isValidToast(view, getPhoneError(phone))) return false;
        if (TextUtils.isEmpty(address)) {
            BaseUtil.toast("家庭住址还未选取");
            return false;
        }
        if (TextUtils.isEmpty(ageRange)) {
            BaseUtil.toast("没有选择年龄段");
            return false;
        }
        if (productId == 0) {
            BaseUtil.toast("选择一下套餐");
            return false;
        }
        if (TextUtils.isEmpty(parentName)) {
            BaseUtil.toast("家长名字还没填写");
            return false;
        }
        if (TextUtils.isEmpty(instructor)) {
            BaseUtil.toast("指导老师还没填写");
            return false;
        }
        return true;
    }
}

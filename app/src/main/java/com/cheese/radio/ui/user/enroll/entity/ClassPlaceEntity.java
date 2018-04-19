package com.cheese.radio.ui.user.enroll.entity;

import com.binding.model.model.ModelView;
import com.cheese.radio.R;

/**
 * Created by 29283 on 2018/4/19.
 */
@ModelView(R.layout.holder_class_place)
public class ClassPlaceEntity {

    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c0/c20/162d14e024d2b.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1523929975%3B3101853175%26q-key-time%3D1523929975%3B3101853175%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D7dbed276b4f213a26cc07938840635fa2e2c2ffc
     * address : 和义大道
     * phone : 18067123456
     * name : 和义大道
     * id : 1
     * traffic : 坐753可直达
     */

    private String image;
    private String address;
    private String phone;
    private String name;
    private int id;
    private String traffic;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }
}


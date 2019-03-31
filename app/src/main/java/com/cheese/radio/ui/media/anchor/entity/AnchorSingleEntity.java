package com.cheese.radio.ui.media.anchor.entity;


import com.cheese.radio.ui.media.play.PlayEntity;

import java.util.ArrayList;

/**
 * Created by 29283 on 2018/3/16.
 */

public class AnchorSingleEntity {





    /**
     * total : 2
     * list : [{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c10/c8/1622425615656.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024853%3B1522118858%26q-key-time%3D1521024853%3B1522118858%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dec060efc36502c1c88f514275bcd29d7cfdfa5b0","playCount":110,"subTitle":"老鼠偷米，快来抓","id":3,"title":"老鼠偷米"},{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c1/c21/1622424a82e43.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024806%3B1522118811%26q-key-time%3D1521024806%3B1522118811%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dac046880fe7a4001532eb41f807c31bfc3cbc87a","playCount":0,"subTitle":"小马独立思考,敢于探索的精神","id":6,"title":"小马过河"}]
     */


    private int total;
    private ArrayList<PlayEntity> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<PlayEntity> getList() {

        if(list!=null)
        return list;
        return new ArrayList<PlayEntity>();
    }

    public void setList(ArrayList<PlayEntity> list) {
        this.list = list;
    }


}
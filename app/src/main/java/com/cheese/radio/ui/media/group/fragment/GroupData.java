package com.cheese.radio.ui.media.group.fragment;

import android.os.Parcel;
import android.os.Parcelable;

import com.cheese.radio.ui.media.anchor.entity.play.item.AnchorSingleItem;


import java.util.List;

/**
 * Created by 29283 on 2018/3/21.
 */

public class GroupData  {
        /**
         * image : http://111.231.237.11:8083/docroot/attachments//cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c22/1622423193536.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024705%3B1522118710%26q-key-time%3D1521024705%3B1522118710%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Df0ef39b00a62b7517831c0c2a42c20a6818b5c33
         * playCount : 15
         * subTitle : 经典童话故事
         * description : 聪明勇敢的阿凡提的故事
         * id : 9
         * title : 聪明的阿凡提
         * contentList : [{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c22/1622423193536.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024705%3B1522118710%26q-key-time%3D1521024705%3B1522118710%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Df0ef39b00a62b7517831c0c2a42c20a6818b5c33","playCount":4,"seconds":0,"subTitle":"聪明的阿凡提","anchorIcon":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c0/c20/162235521045.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021792%3B1522115797%26q-key-time%3D1521021792%3B1522115797%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D9ed5e777f90e04e47433c6c439e06b5b28d03365","location":"PLAY","id":11,"title":"聪明的阿凡提","anchorName":"赵三","url":"http://111.231.237.11:8083/docroot/c20/c14/16222944f2945.mp3","anchorBrief":""},{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c22/1622423193536.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024705%3B1522118710%26q-key-time%3D1521024705%3B1522118710%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Df0ef39b00a62b7517831c0c2a42c20a6818b5c33","playCount":10,"seconds":0,"subTitle":"阿凡提2","anchorIcon":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c0/c20/162235521045.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021792%3B1522115797%26q-key-time%3D1521021792%3B1522115797%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D9ed5e777f90e04e47433c6c439e06b5b28d03365","location":"PLAY","id":16,"title":"阿凡提2","anchorName":"赵三","url":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/audioes/c20/c14/16224133cdc12.mp3?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521023673%3B1522117677%26q-key-time%3D1521023673%3B1522117677%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D91cc6103a03b1cad4daec2793fda1be9917903bf","anchorBrief":""},{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c22/1622423193536.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024705%3B1522118710%26q-key-time%3D1521024705%3B1522118710%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Df0ef39b00a62b7517831c0c2a42c20a6818b5c33","playCount":1,"seconds":0,"subTitle":"阿凡提3","anchorIcon":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c0/c20/162235521045.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021792%3B1522115797%26q-key-time%3D1521021792%3B1522115797%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D9ed5e777f90e04e47433c6c439e06b5b28d03365","location":"PLAY","id":20,"title":"阿凡提3","anchorName":"赵三","url":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/audioes/c20/c14/16224147f2519.mp3?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521023756%3B1522117761%26q-key-time%3D1521023756%3B1522117761%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D43b85ca1c62f9e81b963f0ecd4d468f177b26447","anchorBrief":""}]
         */

        private String image;
        private int playCount;
        private String subTitle;
        private String description;
        private int id;
        private String title;
        private List<AnchorSingleItem> contentList;




    public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<AnchorSingleItem> getContentList() {
            return contentList;
        }

        public void setContentList(List<AnchorSingleItem> contentList) {
            this.contentList = contentList;
        }

        public String getPlayCountText(){
            return String.valueOf(playCount<9999? playCount:(playCount/10000.0+"万"));
        }

}


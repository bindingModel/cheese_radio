package com.cheese.radio.ui.media.play;


import android.view.View;

import com.binding.model.model.inter.Entity;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;

import static com.binding.model.util.BaseUtil.T;

/**
 * Created by 29283 on 2018/3/9.
 */

public class PlayEntity implements Entity {

        /**
         * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c28/1622424e9c762.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024826%3B1522118831%26q-key-time%3D1521024826%3B1522118831%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D599d8ef7d988ba6db4a40dfd7e9cc0af617498c3
         * playCount : 0
         * seconds : 0
         * subTitle : 三只小猪是兄弟,为抵抗大野狼而有不同的遭遇
         * anchorIcon : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c9/c4/1622355798341.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021728%3B1522115733%26q-key-time%3D1521021728%3B1522115733%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D6e742bc08e21317d502091f37816d3ee45beb57b
         * id : 5
         * title : 三只小猪
         * favorCount : 0
         * anchorName : 主播1
         * url : http://111.231.237.11:8083/docroot/attachments/c30/c23/16222876afa12.mp3
         * anchorBrief : <p>一滴水就是一个海洋，一个孩子就是一个世界。陶行知先生曾说：“教人要从小教起，幼儿比如幼苗，必须培养得宜，方能茁壮成长。”我努力使自己从一个“实践型”教师转变为一名“反思型”教师。重视观察孩子，学会解读孩子的一言一行。</p>
         */

        private String image;
        private int playCount;
        private int seconds;
        private String subTitle;
        private String anchorIcon;
        private int id;
        private String title;
        private int favorCount;
        private String anchorName;
        private String url;
        private String anchorBrief;
        private Integer favor;
        private Integer fabu;
        private Integer fileId;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getPlayCount() {
            return playCount;
        }

        public String getPlayViews(){
            return String.valueOf(playCount);
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getSeconds() {
            return seconds;
        }

        public String getLength(){
            return String.valueOf(seconds);
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getAnchorIcon() {
            return anchorIcon;
        }

        public void setAnchorIcon(String anchorIcon) {
            this.anchorIcon = anchorIcon;
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

        public String getFavorCount() {
            return String.valueOf(favorCount);
        }

        public void setFavorCount(int favorCount) {
            this.favorCount = favorCount;
        }

        public String getAnchorName() {
            return anchorName;
        }

        public void setAnchorName(String anchorName) {
            this.anchorName = anchorName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAnchorBrief() {
            return anchorBrief;
        }

        public void setAnchorBrief(String anchorBrief) {
            this.anchorBrief = anchorBrief;
        }

    public Integer getFabu() {
        return fabu;
    }

    public void setFabu(Integer fabu) {
        this.fabu = fabu;
    }

    public CharSequence getMsg() {

        return BaseUtil.colorText(
                T(anchorName, true,"111111"),
                T(anchorBrief, false, "BDBDBD")
        );
    }
    public void onClick(View view){

    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public boolean isFavors(){
        if (favor==null)return false;
        return favor != 0;
    }
    public boolean isFiles(){
        if (fabu==null)return false;
        return fabu != 0;
    }
}

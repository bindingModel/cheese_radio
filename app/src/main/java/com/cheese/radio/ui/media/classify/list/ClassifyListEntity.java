package com.cheese.radio.ui.media.classify.list;

import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;

/**
 * Created by 29283 on 2018/4/12.
 */
@ModelView(R.layout.holder_classify_list)
public class ClassifyListEntity extends ViewInflateRecycler {
        /**
         * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c8/c20/1626b2767a143.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216173%3B3100139373%26q-key-time%3D1522216173%3B3100139373%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D16213da6c7a4471d7273aba0ded330b104392718
         * playCount : 10
         * subTitle : 乌龟和兔子赛跑
         * location : PLAY
         * id : 2
         * title : 龟兔赛跑
         * url : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/audioes/c30/c23/1626b2bf68232.mp3?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216472%3B3100139672%26q-key-time%3D1522216472%3B3100139672%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D9bd7206bb52ef74dc3b536bd667f581240653247
         * fileId : 3
         */

        private String image;
        private int playCount;
        private String subTitle;
        private String location;
        private int id;
        private String title;
        private String url;
        private String fileId;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPlayCount() {
            return String.valueOf(playCount);
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }
    public void onPlayClick(View view) {
        ARouterUtil.itemNavigation("play", id);
    }
    public int getRadius(){
        return 15;
    }
}


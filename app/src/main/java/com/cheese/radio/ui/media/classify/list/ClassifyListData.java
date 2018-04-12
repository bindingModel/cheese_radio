package com.cheese.radio.ui.media.classify.list;

import com.cheese.radio.ui.media.classify.ClassifyEntity;

import java.util.List;

/**
 * Created by 29283 on 2018/4/12.
 */

public class ClassifyListData {
         /**
         * single : {"total":1,"index":1,"list":[{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c8/c20/1626b2767a143.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216173%3B3100139373%26q-key-time%3D1522216173%3B3100139373%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D16213da6c7a4471d7273aba0ded330b104392718","playCount":10,"subTitle":"乌龟和兔子赛跑","location":"PLAY","id":2,"title":"龟兔赛跑","url":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/audioes/c30/c23/1626b2bf68232.mp3?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216472%3B3100139672%26q-key-time%3D1522216472%3B3100139672%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D9bd7206bb52ef74dc3b536bd667f581240653247","fileId":"3"}]}
         */

        private SingleBean single;

        public SingleBean getSingle() {
            return single;
        }

        public void setSingle(SingleBean single) {
            this.single = single;
        }

        public static class SingleBean {
            /**
             * total : 1
             * index : 1
             * list : [{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c8/c20/1626b2767a143.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216173%3B3100139373%26q-key-time%3D1522216173%3B3100139373%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D16213da6c7a4471d7273aba0ded330b104392718","playCount":10,"subTitle":"乌龟和兔子赛跑","location":"PLAY","id":2,"title":"龟兔赛跑","url":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/audioes/c30/c23/1626b2bf68232.mp3?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216472%3B3100139672%26q-key-time%3D1522216472%3B3100139672%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D9bd7206bb52ef74dc3b536bd667f581240653247","fileId":"3"}]
             */

            private int total;
            private int index;
            private List<ClassifyListEntity> list;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public List<ClassifyListEntity> getList() {
                return list;
            }

            public void setList(List<ClassifyListEntity> list) {
                this.list = list;
            }


        }
}

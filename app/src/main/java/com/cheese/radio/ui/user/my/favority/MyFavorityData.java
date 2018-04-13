package com.cheese.radio.ui.user.my.favority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29283 on 2018/4/10.
 */

public class MyFavorityData {

    /**
     * single : {"total":1,"index":0,"list":[{"image":"    _400x400(2).jpg:/c13/c0/1626b2687b35b.jpg","playCount":0,"subTitle":"三只小猪是兄弟,为抵抗大野狼而有不同的遭遇","location":"PLAY","id":5,"title":"三只小猪"}]}
     * group : {"total":0,"index":0,"list":[]}
     */

    private SingleBean single;
    private GroupBean group;

    public SingleBean getSingle() {
        return single = (single != null ? single : new SingleBean());
    }

    public void setSingle(SingleBean single) {
        this.single = single;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public static class SingleBean {
        /**
         * total : 1
         * index : 0
         * list : [{"image":"    _400x400(2).jpg:/c13/c0/1626b2687b35b.jpg","playCount":0,"subTitle":"三只小猪是兄弟,为抵抗大野狼而有不同的遭遇","location":"PLAY","id":5,"title":"三只小猪"}]
         */

        private Integer total;
        private int index;
        private List<MyFavorityEntity> list;

        public String getTotal() {
            return total!=null?String.valueOf(total):null;
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

        public List<MyFavorityEntity> getList() {
            return list = (list != null ? list : new ArrayList<MyFavorityEntity>());
        }

        public void setList(List<MyFavorityEntity> list) {
            this.list = list;
        }

    }

    public static class GroupBean {
        /**
         * total : 0
         * index : 0
         * list : []
         */

        private int total;
        private int index;
        private List<MyFavorityEntity> list;

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

        public List<MyFavorityEntity> getList() {
            return list;
        }

        public void setList(List<MyFavorityEntity> list) {
            this.list = list;
        }
    }

}

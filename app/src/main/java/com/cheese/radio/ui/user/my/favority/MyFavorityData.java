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

    private ListBean single;
    private ListBean group;

    public ListBean getSingle() {
        return single = (single != null ? single : new ListBean());
    }

    public void setSingle(ListBean single) {
        this.single = single;
    }

    public ListBean getGroup() {
        return group = (group != null ? group : new ListBean());
    }

    public void setGroup(ListBean group) {
        this.group = group;
    }

    public static class ListBean {
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
}

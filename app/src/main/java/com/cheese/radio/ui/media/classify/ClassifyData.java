package com.cheese.radio.ui.media.classify;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;

import java.util.List;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(R.layout.item_classify_title)
public class ClassifyData extends ViewInflateRecycler implements SpanSize, GridInflate {

        /**
         * subTagList : [{"tagId":"22","location":"","tagName":"奇幻冒险"},{"tagId":"23","location":"","tagName":"推理智力"},{"tagId":"24","location":"","tagName":"幼儿启蒙"}]
         * tagId : 11
         * icon :
         * location :
         * tagName : 类型
         */

        private String tagId;
        private String icon;
        private String location;
        private String tagName;
        private List<ClassifyEntity> subTagList;

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public List<ClassifyEntity> getSubTagList() {
            return subTagList;
        }

        public void setSubTagList(List<ClassifyEntity> subTagList) {
            this.subTagList = subTagList;
        }

    @Override
    public int getSpanSize() {
        return 3;
    }
    }



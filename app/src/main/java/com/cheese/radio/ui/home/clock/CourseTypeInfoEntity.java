package com.cheese.radio.ui.home.clock;

import com.binding.model.model.ViewInflateRecycler;

/**
 * @name cheese_radio
 * @class name：com.cheese.radio.ui.home.clock
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/8 8:50 PM
 * @change
 * @chang time
 * @class describe
 */
public class CourseTypeInfoEntity extends ViewInflateRecycler {
        /**
         * brief : 简介。。。
         * code : T001
         * name : 一元体验课程
         * id : 1
         * desc : <head style="font-size: 16px;"></head><body><p>详情。。。。。。。</p></body>
         */

        private String brief;
        private String code;
        private String name;
        private int id;
        private String desc;

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


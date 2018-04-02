package com.cheese.radio.ui.media.anchor;


import com.cheese.radio.ui.media.anchor.entity.AnchorSingleEntity;


/**
 * Created by 29283 on 2018/3/16.
 */

public class AnchorData  {

    /**
     * code : 0
     * data : {"single":{"total":2,"list":[{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c10/c8/1622425615656.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024853%3B1522118858%26q-key-time%3D1521024853%3B1522118858%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dec060efc36502c1c88f514275bcd29d7cfdfa5b0","playCount":110,"subTitle":"老鼠偷米，快来抓","id":3,"title":"老鼠偷米"},{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c1/c21/1622424a82e43.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024806%3B1522118811%26q-key-time%3D1521024806%3B1522118811%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dac046880fe7a4001532eb41f807c31bfc3cbc87a","playCount":0,"subTitle":"小马独立思考,敢于探索的精神","id":6,"title":"小马过河"}]},"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c3/c4/1622355532443.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021768%3B1522115773%26q-key-time%3D1521021768%3B1522115773%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D4bdba65648f37dc2743bffbd0c6c2052a45eb882","nickName":"主播2","description":"<p>一滴水就是一个海洋，一个孩子就是一个世界。陶行知先生曾说：\u201c教人要从小教起，幼儿比如幼苗，必须培养得宜，方能茁壮成长。\u201d我努力使自己从一个\u201c实践型\u201d教师转变为一名\u201c反思型\u201d教师。重视观察孩子，学会解读孩子的一言一行。<\/p>","authorId":2}
     */
     /**
         * single : {"total":2,"list":[{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c10/c8/1622425615656.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024853%3B1522118858%26q-key-time%3D1521024853%3B1522118858%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dec060efc36502c1c88f514275bcd29d7cfdfa5b0","playCount":110,"subTitle":"老鼠偷米，快来抓","id":3,"title":"老鼠偷米"},{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c1/c21/1622424a82e43.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024806%3B1522118811%26q-key-time%3D1521024806%3B1522118811%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dac046880fe7a4001532eb41f807c31bfc3cbc87a","playCount":0,"subTitle":"小马独立思考,敢于探索的精神","id":6,"title":"小马过河"}]}
         * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c3/c4/1622355532443.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021768%3B1522115773%26q-key-time%3D1521021768%3B1522115773%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D4bdba65648f37dc2743bffbd0c6c2052a45eb882
         * nickName : 主播2
         * description : <p>一滴水就是一个海洋，一个孩子就是一个世界。陶行知先生曾说：“教人要从小教起，幼儿比如幼苗，必须培养得宜，方能茁壮成长。”我努力使自己从一个“实践型”教师转变为一名“反思型”教师。重视观察孩子，学会解读孩子的一言一行。</p>
         * authorId : 2
         */

        private AnchorSingleEntity single;
        private String image;
        private String nickName;
        private String description;
        private int authorId;

        public AnchorSingleEntity getSingle() {
            if(single!=null)
            return single;
            return new AnchorSingleEntity();
        }

        public void setSingle(AnchorSingleEntity single) {
            this.single = single;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }



}

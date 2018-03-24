package com.cheese.radio.ui.media.anchors;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.ui.Constant;

import java.util.List;

import static com.binding.model.util.BaseUtil.T;
import static com.cheese.radio.inject.component.ActivityComponent.Router.author;

/**
 * Created by 29283 on 2018/3/8.
 */
@ModelView(R.layout.item_home_anchors)
public class AnchorsItem extends ViewInflateRecycler {


    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c9/c4/1622355798341.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021728%3B1522115733%26q-key-time%3D1521021728%3B1522115733%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D6e742bc08e21317d502091f37816d3ee45beb57b
     * nickName : 主播1
     * description :  <p>一滴水就是一个海洋，一个孩子就是一个世界。陶行知先生曾说：“教人要从小教起，幼儿比如幼苗，必须培养得宜，方能茁壮成长。”我努力使自己从一个“实践型”教师转变为一名“反思型”教师。重视观察孩子，学会解读孩子的一言一行。</p>
     * authorId : 1
     */

    private String image;
    private String nickName;
    private String description;
    private int authorId;

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

    public CharSequence getMsg() {

        return BaseUtil.colorText(
                T(nickName, true, R.color.text_black, 1),
                T(description, false, R.color.text_gray)
        );
    }

    public void onClick(View view){
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.authorId, authorId);
            switch (view.getId()){
                case R.id.care_anchor :
                case R.id.frame_layout :
                    ARouterUtil.navigation(author, bundle);
            }
    }
}

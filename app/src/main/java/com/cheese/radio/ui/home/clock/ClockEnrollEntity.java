package com.cheese.radio.ui.home.clock;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.binding.model.App;
import com.binding.model.Config;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;

import static com.cheese.radio.ui.Constant.courseTypeInfo;

@ModelView(R.layout.holder_clock_enroll)
public class ClockEnrollEntity extends ViewInflateRecycler {
    private int position;


    /**
     * brief : 简介。。。
     * number : 0
     * code : T001
     * name : 一元体验课程
     * id : 1
     * maa : false
     * pao : true
     */

    private String brief;
    private int number;
    private String code;
    private String name;
    private int id;
    private boolean maa;
    private boolean pao;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public boolean isMaa() {
        return maa;
    }

    public void setMaa(boolean maa) {
        this.maa = maa;
    }

    public boolean isPao() {
        return pao;
    }

    public void setPao(boolean pao) {
        this.pao = pao;
    }
    public ClockEnrollEntity(int position) {
        this.position = position;
    }

    public String getPosition() {
        StringBuilder builder =new StringBuilder();
        if(getHolder_position()<9)builder.append("0");
        builder.append(getHolder_position()+1);
        return builder.toString();
    }

    public String getTitle() {
        return name;
        /*switch (position) {
            case 0:return "一元体验课";
            case 1:return "标准课";
            case 2:return "芝士妈妈课";
            case 3:return "趣声社";
            case 4:return "声宝课";
            default:return "未知项目";
        }*/
    }
    public Drawable getTitleBg(){
        switch (name){
            case "一元体验课":return App.getDrawable(R.mipmap.tyk);
            case "标准课":return App.getDrawable(R.mipmap.bzk);
            case "芝士妈妈课":return App.getDrawable(R.mipmap.zsk);
            case "趣声社":return App.getDrawable(R.mipmap.qs);
            case "声宝课":return App.getDrawable(R.mipmap.sbk);
            default:return App.getDrawable(R.mipmap.tyk);
        }
    }
    public Drawable getPao(){
        return App.getDrawable(pao?R.mipmap.xd:R.mipmap.xd2);
    }
    public Drawable getMaa(){
        return App.getDrawable(!maa?R.mipmap.yue:R.mipmap.yue2);
    }
    public void onInfoClick(View view) {
        Bundle bundle =new Bundle();
        bundle.putInt(Constant.id,id);
        bundle.putString(Config.title,name);
        bundle.putString(Constant.method,courseTypeInfo);
        ARouterUtil.navigation(ActivityComponent.Router.activityDetail,bundle);
    }
    public void onEnrollClick(View view){
        if(!pao){
            BaseUtil.toast(view.getContext().getString(R.string.have_enroll_tip));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.courseTypeId,id);
        ARouterUtil.navigation(ActivityComponent.Router.enroll,bundle);
    }
    public void onBookClick(View view){
        if(!maa){
            BaseUtil.toast(view.getContext().getString(R.string.un_enroll_tip));
            return;
        }
        Bundle bundle =new Bundle();
        bundle.putInt(Constant.courseTypeId,id);
        bundle.putString(Config.title,name);
        ARouterUtil.navigation(ActivityComponent.Router.calendar,bundle);
    }
}

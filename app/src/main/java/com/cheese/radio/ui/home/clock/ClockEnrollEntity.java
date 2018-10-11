package com.cheese.radio.ui.home.clock;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.binding.model.App;
import com.binding.model.Config;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;

import static com.cheese.radio.ui.Constant.activityInfo;
import static com.cheese.radio.ui.Constant.bundle;
import static com.cheese.radio.ui.Constant.courseTypeId;
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
        return code.substring(2);
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
        switch (code){
            case "T001":return App.getDrawable(R.mipmap.tyk);
            case "T002":return App.getDrawable(R.mipmap.bzk);
            case "T003":return App.getDrawable(R.mipmap.zsk);
            case "T004":return App.getDrawable(R.mipmap.qs);
            case "T005":return App.getDrawable(R.mipmap.sbk);
            default:return null;
        }
    }
    public void onInfoClick(View view) {
        Bundle bundle =new Bundle();
        bundle.putInt(Constant.id,id);
        bundle.putString(Config.title,name);
        bundle.putString(Constant.method,courseTypeInfo);
        ARouterUtil.navigation(ActivityComponent.Router.activityDetail,bundle);
    }
    public void onEnrollClick(View view){
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.courseTypeId,id);
        ARouterUtil.navigation(ActivityComponent.Router.enroll,bundle);
    }
    public void onBookClick(View view){
        Bundle bundle =new Bundle();
        bundle.putInt(Constant.courseTypeId,id);
        ARouterUtil.navigation(ActivityComponent.Router.calendar,bundle);
    }
}

package com.cheese.radio.ui.home.page.banner;

import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.binding.model.adapter.IEventAdapter;
import com.binding.model.adapter.ILayoutAdapter;
import com.binding.model.model.inter.Inflate;
import com.binding.model.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:33
 * modify developer：  admin
 * modify time：17:33
 * modify remark：
 *
 * @version 2.0
 */


public class ViewPagerAdapter<E extends Inflate> extends PagerAdapter implements ILayoutAdapter<E> ,IEventAdapter<E>{
    private List<E> list = new ArrayList<>();
    private int count = Integer.MAX_VALUE;
    private final IEventAdapter<E> iEntityAdapter = this;

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count == Integer.MAX_VALUE ? list.size() : count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        E e = list.get(position % count);
        ViewDataBinding binding = e.attachView(container.getContext(), container, false, e.getDataBinding());
        e.setIEventAdapter(iEntityAdapter);
        View v = binding.getRoot();
        if (container.equals(v.getParent())) container.removeView(v);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        E e = list.get(position % count);
        container.removeView((View)object);
    }

    @Override
    public boolean setList(int position, List<? extends E> e, int type) {
        boolean done = BaseUtil.setList(list, position, e, type);
        if (done) notifyDataSetChanged();
        return done;
    }


    @Override
    public boolean setEntity(int position, E f, int type, View view){
        return setIEntity(position, f, type, view);
    }

    @Override
    public boolean setIEntity(int position, E f, int type, View view){
        boolean done = BaseUtil.setEntity(list, position, f, type);
        if (done) notifyDataSetChanged();
        return done;
    }

    @Override
    public int getItemPosition(Object object) {
        if (getCount() > 0) return POSITION_NONE;
        return super.getItemPosition(object);
    }

    @Override
    public List<E> getList() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
}

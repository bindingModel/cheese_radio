package com.cheese.radio.ui.media.picture;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.bumptech.glide.Glide;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityPictureBinding;
import com.cheese.radio.ui.Constant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@ModelView(R.layout.activity_picture)
public class PictureModel extends ViewHttpModel<PictureActivity, ActivityPictureBinding, PictureEntity> implements ViewPager.OnPageChangeListener {
    @Inject
    PictureModel() {
    }

    @Override
    public void accept(PictureEntity pictureEntity) throws Exception {

    }

    private ViewPager viewPager;
    private AdapterViewpager adapter;
    private TextView textView;
    private int listSize;

    @Override
    public void attachView(Bundle savedInstanceState, PictureActivity pictureActivity) {
        super.attachView(savedInstanceState, pictureActivity);
        List<String> urlList = getT().getIntent().getStringArrayListExtra(Constant.urlList);
        listSize = urlList.size();
        viewPager = getDataBinding().viewPager;
        adapter = new AdapterViewpager(getViewList(urlList));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        textView = getDataBinding().pictureNum;
        viewPager.setCurrentItem(0);
        textView.setText(String.format("(%d/%d)", 1, listSize));

    }

    private List<View> getViewList(List<String> list) {
        List<View> mlist = new ArrayList<>();
        if (list == null || list.size() == 0) return mlist;
        for (String url : list) {
            View v = View.inflate(getT(), R.layout.item_picture, null);
            ImageView mItemPager = v.findViewById(R.id.imageView);
            Glide.with(v).load(url).into(mItemPager);
            mlist.add(v);
        }
        return mlist;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        textView.setText(String.format("(%d/%d)", position+1, listSize));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class AdapterViewpager extends PagerAdapter {
        private List<View> views;

        public AdapterViewpager(List<View> mView) {
            this.views = mView;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ViewGroup parent = (ViewGroup) views.get(position).getParent();
            if (parent != null) parent.removeAllViews();
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(views.get(position));
//            ((ViewGroup) container).removeView((View) object);
//            object=null;
        }
    }
}

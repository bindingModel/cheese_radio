package com.cheese.radio.ui.startup.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.ActivityWelcomeBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.login;

@ModelView(R.layout.activity_welcome)
public class WelcomeModel extends ViewModel<WelcomeActivity, ActivityWelcomeBinding> implements ViewPager.OnPageChangeListener ,View.OnClickListener {
    @Inject
    WelcomeModel() {
    }

    private AdapterViewpager mAdapter;
    private ViewPager mViewPager;
    private List<View> mList;
    private LinearLayout mllLayout;

    @Override
    public void attachView(Bundle savedInstanceState, WelcomeActivity activity) {
        super.attachView(savedInstanceState, activity);
        mViewPager = getDataBinding().viewPager;
        mList = new ArrayList<>();
        mllLayout = getDataBinding().linearLayout;
        mllLayout.getChildAt(0).setEnabled(false);
        for (int i = 0; i < 3; i++) {
            mList.add(getWelcomePage(i));
        }
        mAdapter = new AdapterViewpager(mList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    private View getWelcomePage(int i) {

        View v = View.inflate(getT(), R.layout.item_welcome, null);
        ImageView mItemPager = v.findViewById(R.id.imageView);
        Button touchGO = v.findViewById(R.id.touch_go);
        switch (i) {
            case 0:
                mItemPager.setImageResource(R.mipmap.welcome_1);
                touchGO.setVisibility(View.GONE);
                break;
            case 1:
                mItemPager.setImageResource(R.mipmap.welcome_2);
                touchGO.setVisibility(View.GONE);
                break;
            case 2: {
                mItemPager.setImageResource(R.mipmap.welcome_3);
                touchGO.setOnClickListener(this);
//                Button welcomeButton=new Button(getT());
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    welcomeButton.setBackground(getT().getDrawable(R.drawable.welcome_shape));
//                }else welcomeButton.setBackgroundDrawable(getT().getResources().getDrawable(R.drawable.welcome_shape));
            }


        }
        return v;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        clearIndicatorFocusedState();
        mllLayout.getChildAt(position).setEnabled(false);
        mllLayout.setVisibility(View.VISIBLE);
        if(position==2)mllLayout.setVisibility(View.GONE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void clearIndicatorFocusedState() {
        int childCount = mllLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            mllLayout.getChildAt(i).setEnabled(true);
        }
    }


    @Override
    public void onClick(View v) {
        ARouterUtil.navigation(login);
        getT().finish();
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


package com.cheese.radio.ui.media.anchor;

import android.Manifest;
import android.databinding.ObservableBoolean;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;

import com.android.databinding.library.baseAdapters.BR;
import com.binding.model.App;
import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityAnchorBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.entity.AnchorEntity;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

import static com.cheese.radio.ui.service.AudioService.Prepared;

/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(value = R.layout.activity_anchor, model = true)
public class AnchorModel extends PagerModel<AnchorActivity, ActivityAnchorBinding, AnchorEntity> {

    @Inject
    AnchorModel(@ActivityFragmentManager FragmentManager manager) {
        super(new FragmentAdapter<>(manager));
    }

    @Inject
    RadioApi api;
    private Integer authorId;
    private AnchorParams params;
    private final List<AnchorEntity> list = new ArrayList<>();
    private AnchorData anchorData;
    private AudioServiceUtil util = AudioServiceUtil.getInstance();
    private PlayEntity entity;
    public transient ObservableBoolean checked = new ObservableBoolean();
    private List<PlayEntity> fmsEntities = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, AnchorActivity anchorActivity) {
        super.attachView(savedInstanceState, anchorActivity);
        authorId = getT().getIntent().getIntExtra(Constant.authorId, 0);
        params = new AnchorParams("info", authorId);
        addDisposable(api.getAuthor(params).compose(new RestfulTransformer<>()).subscribe(anchorData -> {
            getDataBinding().setEntity(anchorData);
            setFragment(anchorData);
            getDataBinding().anchorData.setText("作品（" + anchorData.getSingle().getList().size() + ")");
        }));
    }

    public void setFragment(AnchorData anchorData) {
        list.add(new AnchorEntity(anchorData));
        list.add(new AnchorEntity(anchorData));
        try {
            accept(list);
            setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPlayClick(View view) {
        boolean playing = util.isPlaying();
        if (!playing) {
            if (!util.play()){
                if(entity!=null)play(entity);
            }else
                checked.set(true);
        } else {
            util.pause();
            checked.set(false);
        }
    }
    private void play(PlayEntity entity) {
        this.entity = entity;
        BaseUtil.checkPermission(App.getCurrentActivity(), aBoolean -> {
            if (aBoolean && util.start(transformUrl(entity), this, listener)==Prepared)
                getPlayView().setEnabled(false);
        }, Manifest.permission.RECORD_AUDIO);
        getDataBinding().setVariable(BR.entity, entity);
    }
    protected String transformUrl(PlayEntity playEntity) {
        return playEntity.getUrl();
    }
    private final MediaPlayer.OnPreparedListener listener = this::onPrepared;
    public void onPrepared(MediaPlayer mediaPlayer){
        mediaPlayer.start();
        getPlayView().setEnabled(true);
        checked.set(true);
        util.setUri(transformUrl(fmsEntities.get(0)));
    }

    public RadioButton getPlayView() {
        return getDataBinding().play;
    }

}

package com.cheese.radio.ui.media.play;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import com.binding.model.adapter.AdapterType;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.Entity;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.util.MyBaseUtil;

import static com.binding.model.util.BaseUtil.T;

/**
 * Created by 29283 on 2018/3/9.
 */

@ModelView(R.layout.item_anchor_single)

public class PlayEntity extends ViewInflateRecycler implements Entity, Parcelable {

    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c28/1622424e9c762.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024826%3B1522118831%26q-key-time%3D1521024826%3B1522118831%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D599d8ef7d988ba6db4a40dfd7e9cc0af617498c3
     * playCount : 0
     * seconds : 0
     * subTitle : 三只小猪是兄弟,为抵抗大野狼而有不同的遭遇
     * anchorIcon : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c9/c4/1622355798341.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521021728%3B1522115733%26q-key-time%3D1521021728%3B1522115733%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D6e742bc08e21317d502091f37816d3ee45beb57b
     * id : 5
     * title : 三只小猪
     * favorCount : 0
     * anchorName : 主播1
     * url : http://111.231.237.11:8083/docroot/attachments/c30/c23/16222876afa12.mp3
     * anchorBrief : <p>一滴水就是一个海洋，一个孩子就是一个世界。陶行知先生曾说：“教人要从小教起，幼儿比如幼苗，必须培养得宜，方能茁壮成长。”我努力使自己从一个“实践型”教师转变为一名“反思型”教师。重视观察孩子，学会解读孩子的一言一行。</p>
     */

    private String image;
    private int playCount;
    private int seconds;
    private transient String subTitle;
    private String anchorIcon;
    private int id;
    private String title;
    private int favorCount;
    private transient int fabuCount;
    private transient String anchorName;
    private String url;
    private transient String anchorBrief;
    private transient Integer favor;
    private transient Integer fabu;
    private transient Integer fileId;
    private String shareUrl;
    private transient String shareLandingUrl;
    private String location;
    private transient int audioSize;
    private int hasWengao;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAudioSize() {

        return audioSize;
    }

    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    public String getShareLandingUrl() {
        return shareLandingUrl;
    }

    public void setShareLandingUrl(String shareLandingUrl) {
        this.shareLandingUrl = shareLandingUrl;
    }

    public String getFabuCount() {
        return String.valueOf(fabuCount);
    }

    public void setFabuCount(int fabuCount) {
        this.fabuCount = fabuCount;
    }

    public void addFabuCount(int i) {
        fabuCount += i;
        if (fabuCount < 0) fabuCount = 0;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlayCount() {
        return String.valueOf(playCount);
    }

    public String getPlayViews() {
        return String.valueOf(playCount);
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getLength() {
//            return String.valueOf(seconds);
        return "00:00";
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getSubTitle() {
        if (TextUtils.isEmpty(subTitle)) return " ";
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAnchorIcon() {
        return anchorIcon;
    }

    public void setAnchorIcon(String anchorIcon) {
        this.anchorIcon = anchorIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFavorCount() {
        if (favorCount > 1000) return String.valueOf(favorCount / 1000) + "+";
        return String.valueOf(favorCount);
    }

    public void setFavorCount(int favorCount) {
        this.favorCount = favorCount;
    }


    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAnchorBrief() {
        return TextUtils.isEmpty(anchorBrief) ? "" : anchorBrief;
    }

    public void setAnchorBrief(String anchorBrief) {
        this.anchorBrief = anchorBrief;
    }

    public Integer getFabu() {
        return fabu;
    }

    public void setFabu(Integer fabu) {
        this.fabu = fabu;
    }

    public CharSequence getMsg() {

        return BaseUtil.colorText(
                T(anchorName, true, "111111"),
                T(anchorBrief, false, "BDBDBD")
        );
    }

    public void onClick(View view) {

    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public boolean isFavors() {
        if (favor == null) return false;
        return favor != 0;
    }

    public boolean isFiles() {
        if (fabu == null) return false;
        return fabu != 0;
    }

    public String getAudioSizeString() {
        if (audioSize == 0) return "0个故事";
        return audioSize + "个故事";
    }

    public String getSecondsString() {
        if (seconds == 0) return "00:00";
        return MyBaseUtil.getMinute(seconds);
    }

    public PlayEntity() {
    }

    /**
     * 点击播放按钮后，应该回调跳转到对应的曲目
     *
     * @param view
     */
    public void onPlayClick(View view) {
        if (getIEventAdapter() != null) {
            getIEventAdapter().setEntity(0, this, AdapterType.add, view);
        }
//        ARouterUtil.itemNavigation(location, id);
    }

    public int getRadius() {
        return 15;
    }

    private int anchorId;

    public int getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(int anchorId) {
        this.anchorId = anchorId;
    }

    public int getHasWengao() {
        return hasWengao;
    }

    public void setHasWengao(int hasWengao) {
        this.hasWengao = hasWengao;
    }

    public int getWengao(){
        return hasWengao == 0?View.GONE: View.VISIBLE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeInt(this.playCount);
        dest.writeInt(this.seconds);
        dest.writeString(this.anchorIcon);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.favorCount);
        dest.writeString(this.url);
        dest.writeInt(this.hasWengao);
        dest.writeInt(this.anchorId);
    }

    protected PlayEntity(Parcel in) {
        this.image = in.readString();
        this.playCount = in.readInt();
        this.seconds = in.readInt();
        this.anchorIcon = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.favorCount = in.readInt();
        this.url = in.readString();
        this.hasWengao = in.readInt();
        this.anchorId = in.readInt();
    }

    public static final Creator<PlayEntity> CREATOR = new Creator<PlayEntity>() {
        @Override
        public PlayEntity createFromParcel(Parcel source) {
            return new PlayEntity(source);
        }

        @Override
        public PlayEntity[] newArray(int size) {
            return new PlayEntity[size];
        }
    };
}

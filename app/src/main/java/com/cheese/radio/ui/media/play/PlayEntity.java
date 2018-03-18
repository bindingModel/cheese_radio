package com.cheese.radio.ui.media.play;


import com.binding.model.model.inter.Entity;

/**
 * Created by 29283 on 2018/3/9.
 */

public class PlayEntity implements Entity {
    private String url ="https://bookrental.oss-cn-shanghai.aliyuncs.com/audios/hxxdzg.mp3";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public Bitmap RSblur(Bitmap src){
//        long startTime = System.currentTimeMillis();
//        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(),src.getHeight(), Bitmap.Config.ARGB_8888);
//
//        RenderScript renderScript = RenderScript.create(IkeApplication.getApp());
//        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
//
//        Allocation allIn = Allocation.createFromBitmap(renderScript, src);
//        Allocation allOut = Allocation.createFromBitmap(renderScript,bitmap);
//
//        // 控制模糊程度
//        scriptIntrinsicBlur.setRadius(2.f);
//
//        scriptIntrinsicBlur.setInput(allIn);
//        scriptIntrinsicBlur.forEach(allOut);
//
//        allOut.copyTo(bitmap);
//
//        src.recycle();
//        renderScript.destroy();
//
//        long endTime = System.currentTimeMillis();
//
//        Log.e("tag","this is RenderScript blur use time "+(endTime-startTime)+"ms");
//        return bitmap;
//    }
}

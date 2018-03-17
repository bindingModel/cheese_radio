package com.cheese.radio.ui.media.play;


import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

import com.binding.model.model.inter.Entity;
import com.cheese.radio.ui.IkeApplication;

/**
 * Created by 29283 on 2018/3/9.
 */

public class PlayEntity implements Entity {



    public Bitmap RSblur(Bitmap src){
        long startTime = System.currentTimeMillis();
        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(),src.getHeight(), Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(IkeApplication.getApp());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));

        Allocation allIn = Allocation.createFromBitmap(renderScript, src);
        Allocation allOut = Allocation.createFromBitmap(renderScript,bitmap);

        // 控制模糊程度
        scriptIntrinsicBlur.setRadius(2.f);

        scriptIntrinsicBlur.setInput(allIn);
        scriptIntrinsicBlur.forEach(allOut);

        allOut.copyTo(bitmap);

        src.recycle();
        renderScript.destroy();

        long endTime = System.currentTimeMillis();

        Log.e("tag","this is RenderScript blur use time "+(endTime-startTime)+"ms");
        return bitmap;
    }
}

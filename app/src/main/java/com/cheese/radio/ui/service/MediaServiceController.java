package com.cheese.radio.ui.service;

import java.io.IOException;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:18
 * modify developer：  admin
 * modify time：15:18
 * modify remark：
 *
 * @version 2.0
 */


public interface MediaServiceController {
    int start(String uri) throws IOException;
    boolean play();
    void pause();
    long getCurrentPosition();
    long getDuration();
    boolean isPlaying();
    void seekTo(long changeProgress);
    void addListener(Object listener);

    void setStatus(int reset);
    int getStatus();
}

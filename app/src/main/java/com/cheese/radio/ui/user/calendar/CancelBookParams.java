package com.cheese.radio.ui.user.calendar;

import com.cheese.radio.inject.api.ContentParams;

/**
 * @name cheese_radio
 * @class name：com.cheese.radio.ui.user.calendar
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/15 9:02 PM
 * @change
 * @chang time
 * @class describe
 */
public class CancelBookParams extends ContentParams {
    private int bookId;

    public CancelBookParams(String method) {
        super(method);
    }

    public CancelBookParams() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}

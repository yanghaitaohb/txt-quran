package com.quran.audio.message;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MessageEvent {
    public final static int PLAY = 1, PLAY_POSITION = 2, STOP = 3;
    public final static int LESSON = 4, CHAPTER = 5, VOLUME = 6;
    @IntDef({PLAY, STOP, PLAY_POSITION, LESSON, CHAPTER, VOLUME})
    @Retention(RetentionPolicy.SOURCE)
    public @interface action{}

    private int action;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAction() {
        return action;
    }

    public void setAction(@action int action) {
        this.action = action;
    }

    public MessageEvent(@action int action) {
        this.action = action;
    }

    public MessageEvent(int action, int position) {
        this.action = action;
        this.position = position;
    }
}

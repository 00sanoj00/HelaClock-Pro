package top.defaults.colorpickerr;

import android.view.MotionEvent;

class ThrottledTouchEventHandler {

    private int minInterval = top.defaults.colorpickerr.Constants.EVENT_MIN_INTERVAL;
    private top.defaults.colorpickerr.Updatable updatable;
    private long lastPassedEventTime = 0;

    ThrottledTouchEventHandler(top.defaults.colorpickerr.Updatable updatable) {
        this(top.defaults.colorpickerr.Constants.EVENT_MIN_INTERVAL, updatable);
    }

    private ThrottledTouchEventHandler(int minInterval, Updatable updatable) {
        this.minInterval = minInterval;
        this.updatable = updatable;
    }

    void onTouchEvent(MotionEvent event) {
        if (updatable == null) return;
        long current = System.currentTimeMillis();
        if (current - lastPassedEventTime <= minInterval) {
            return;
        }
        lastPassedEventTime = current;
        updatable.update(event);
    }
}

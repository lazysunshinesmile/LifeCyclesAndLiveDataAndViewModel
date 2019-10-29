package com.example.android.lifecycles.step1;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

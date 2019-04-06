package com.example.calenderapp;

import android.view.View;

public class Handler implements View.OnClickListener {
    private MainActivity mainActivity;
    private int sortStyle;

    public Handler(MainActivity mainActivity, int sortStyle) {
        this.mainActivity = mainActivity;
        this.sortStyle = sortStyle;
    }
    @Override
    public void onClick(View v) {
        mainActivity.sortEvent(sortStyle);
    }
}

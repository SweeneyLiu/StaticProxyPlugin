package com.lsw.plugin1;

import android.os.Bundle;

import com.lsw.pluginlibrary.BasePluginActivity;

/**
 * Created by sweeneyliu on 2018/11/15.
 */
public class SecondActivity extends BasePluginActivity {
    private static final String TAG = "SecondActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
    }
}

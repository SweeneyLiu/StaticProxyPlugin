package com.lsw.pluginlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sweeneyliu on 2018/11/26.
 */
public interface IRemoteActivity {
    public void onStart();
    public void onRestart();
    public void onActivityResult(int requestCode, int resultCode, Intent data);
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onCreate(Bundle savedInstanceState);
    public void setProxy(Activity proxyActivity, String dexPath);

    /**
     * 获取当前Activity的启动模式
     */
    public int getLaunchMode();
}

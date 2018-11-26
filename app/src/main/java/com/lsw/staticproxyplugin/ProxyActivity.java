package com.lsw.staticproxyplugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.lsw.pluginlibrary.AppConstants;
import com.lsw.pluginlibrary.IRemoteActivity;
import com.lsw.staticproxyplugin.BaseHostActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ProxyActivity extends BaseHostActivity {

    private static final String TAG = "ProxyActivity";

    private String mClass;
    private HashMap<String, Method> mActivityLifecircleMethods = new HashMap<String, Method>();

    private IRemoteActivity mRemoteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDexPath = getIntent().getStringExtra(AppConstants.EXTRA_DEX_PATH);
        mClass = getIntent().getStringExtra(AppConstants.EXTRA_CLASS);

        loadClassLoader();
        loadResources();

        launchTargetActivity(mClass);
    }

    void launchTargetActivity(final String className) {
        try {
            //反射出插件的Activity对象
            Class<?> localClass = dexClassLoader.loadClass(className);
            Constructor<?> localConstructor = localClass.getConstructor(new Class[] {});
            Object instance = localConstructor.newInstance(new Object[] {});

            mRemoteActivity = (IRemoteActivity) instance;
            mRemoteActivity.setProxy(this, mDexPath);

            //执行插件Activity的onCreate方法
            Bundle bundle = new Bundle();
            mRemoteActivity.onCreate(bundle);

            //执行插件Activity的setProxy方法，建立双向引用
            Method setProxy = localClass.getMethod("setProxy", new Class[] { Activity.class, String.class });
            setProxy.setAccessible(true);
            setProxy.invoke(instance, new Object[] { this, mDexPath });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult resultCode=" + resultCode);
        mRemoteActivity.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRemoteActivity.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mRemoteActivity.onRestart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mRemoteActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRemoteActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRemoteActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRemoteActivity.onDestroy();
    }
}

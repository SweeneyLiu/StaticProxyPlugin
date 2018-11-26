package com.tencent.plugin2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lsw.pluginlibrary.AppConstants;
import com.lsw.pluginlibrary.BasePluginActivity;
import com.lsw.pluginlibrary.MyPlugins;

public class MainActivity extends BasePluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity，插件外跳转
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plugin1DexPath = MyPlugins.plugins.get("plugin1.apk");

                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, plugin1DexPath);
                intent.putExtra(AppConstants.EXTRA_CLASS, "com.lsw.staticproxyplugin.SecondActivity");
                startActivity(intent);
            }
        });
    }
}

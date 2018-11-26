package com.lsw.plugin1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lsw.pluginlibrary.BasePluginActivity;

/**
 * Created by sweeneyliu on 2018/11/23.
 */
public class TestActivity extends BasePluginActivity {

    private static final String TAG = "Client-TestActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);

        Button button = (Button)findViewById(R.id.button1);
        button.setText(getResources().getString(R.string.test));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("username", "sweeneyliu");
                that.setResult(2, intent);
                finish();
            }
        });
    }
}

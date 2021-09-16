package com.plugin.mini;

import android.os.Bundle;
import android.widget.TextView;

import com.plugin.minilib.PluginActivity;

public class TestActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textView = new TextView(this);
        textView.setText("plugins");
        setContentView(textView);
    }
}

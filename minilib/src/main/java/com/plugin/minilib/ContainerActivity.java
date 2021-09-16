package com.plugin.minilib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ContainerActivity extends AppCompatActivity {

    private PluginActivity mPluginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String pluginActivityName = getIntent().getStringExtra("pluginActivityName");
        mPluginActivity = PluginLoader.loadActivity(this, pluginActivityName);
        if (mPluginActivity == null) {
            super.onCreate(savedInstanceState);
            return;
        }

        mPluginActivity.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        if (mPluginActivity == null) {
            super.onStart();
            return;
        }
        mPluginActivity.onStart();
    }

    @Override
    protected void onResume() {
        if (mPluginActivity == null) {
            super.onResume();
            return;
        }
        mPluginActivity.onResume();
    }

    @Override
    protected void onPause() {
        if (mPluginActivity == null) {
            super.onPause();
            return;
        }
        mPluginActivity.onPause();
    }

    @Override
    protected void onStop() {
        if (mPluginActivity == null) {
            super.onStop();
            return;
        }
        mPluginActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mPluginActivity == null) {
            super.onDestroy();
            return;
        }
        mPluginActivity.onDestroy();
    }
}
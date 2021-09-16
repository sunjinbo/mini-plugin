package com.plugin.minilib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class PluginActivity extends Activity {
    private ContainerActivity mContainerActivity;

    public void setContainerActivity(ContainerActivity containerActivity) {
        mContainerActivity = containerActivity;
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (mContainerActivity == null) return findViewById(id);
        return mContainerActivity.findViewById(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

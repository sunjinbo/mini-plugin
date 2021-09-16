package com.plugin.mini;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.plugin.minilib.MiniPlugin;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    private static final String PLUGINS_FILE_NAME = "plugins.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyAssets();
    }

    public void onStartPluginClick(View view) {
        File apkDir = this.getExternalFilesDir(null);
        File apkFile = new File(apkDir, PLUGINS_FILE_NAME);
        try {
            MiniPlugin.startPlugin(this, apkFile.getAbsolutePath(), "com.plugin.mini.TestActivity");
        } catch (ClassNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void copyAssets() {
        File dstDir = this.getExternalFilesDir(null);
        File dstFile = new File(dstDir, PLUGINS_FILE_NAME);

        InputStream is = null;
        FileOutputStream fos = null;

        try {
            is = getResources().getAssets().open(PLUGINS_FILE_NAME);
            fos = new FileOutputStream(dstFile);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
        } catch (IOException e) {
            closeFile(fos);
            closeFile(is);
        }
    }

    private static void closeFile(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

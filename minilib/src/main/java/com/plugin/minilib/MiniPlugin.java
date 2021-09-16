package com.plugin.minilib;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dalvik.system.DexClassLoader;

public class MiniPlugin {
    private static DexClassLoader sClassLoader;

    public static void startPlugin(Context context, String pluginApkPath, String pluginActivityName) throws IOException, ClassNotFoundException {
        installPlugin(context, pluginApkPath);
        launchPluginActivity(context, pluginActivityName);
    }

    public static DexClassLoader getClassLoader() {
        return sClassLoader;
    }

    private static void installPlugin(Context context, String pluginApkPath) throws IOException {
        File apkFile = new File(pluginApkPath);
        if (apkFile.exists() && apkFile.isFile()) {
            File internalDir = context.getFilesDir();
            // 将apk文件复制到/data/user/0/<应用包名>/files目录下
            final File pluginFile = copyFile(apkFile, internalDir);
            if (!pluginFile.exists()) throw new FileNotFoundException("Plugin apk file not found.");

            // 获取插件文件路径、原生lib存放路径、优化dex存放路径
            final String pluginPath = pluginFile.getAbsolutePath();
            final String nativeLibDir = new File(internalDir, "pluginLib").getAbsolutePath();
            final String dexOutDir = new File(internalDir, "dexOut").getAbsolutePath();

            // 创建类加载器
            sClassLoader = new DexClassLoader(pluginPath, nativeLibDir, dexOutDir, context.getClassLoader());
        } else {
            throw new FileNotFoundException("Plugin apk file not found.");
        }
    }

    private static void launchPluginActivity(Context context, String pluginActivityName) throws ClassNotFoundException {
        // ClassLoader 注入
        sClassLoader.loadClass(pluginActivityName);

        // 启动容器类加载插件Activity执行其生命周期
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra("pluginActivityName", pluginActivityName);
        context.startActivity(intent);
    }

    private static File copyFile(File srcFile, File dstDir) throws IOException {
        File dstFile = new File(dstDir, srcFile.getName());

        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream(srcFile);
            output = new FileOutputStream(dstFile);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }

        return dstFile;
    }
}

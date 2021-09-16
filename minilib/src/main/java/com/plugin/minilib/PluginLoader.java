package com.plugin.minilib;

import android.content.Context;

import dalvik.system.DexClassLoader;

public class PluginLoader {
    public static PluginActivity loadActivity(Context context, String activityName) {
        DexClassLoader classLoader = MiniPlugin.getClassLoader();
        if (classLoader != null) {
            try {
                Class clazz = classLoader.loadClass(activityName);
                return (PluginActivity) clazz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

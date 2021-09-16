package com.plugin.minilib;

import android.content.res.Resources;

public class PluginResources extends Resources {
    private Resources hostResources;
    private Resources injectResources;

    public PluginResources(Resources hostResources, Resources injectResources) {
        super(injectResources.getAssets(), injectResources.getDisplayMetrics(), injectResources.getConfiguration());
        this.hostResources = hostResources;
        this.injectResources = injectResources;
    }

    @Override
    public String getString(int id, Object... formatArgs) throws NotFoundException {
        try {
            return injectResources.getString(id, formatArgs);
        } catch (NotFoundException e) {
            return hostResources.getString(id, formatArgs);
        }
    }

}

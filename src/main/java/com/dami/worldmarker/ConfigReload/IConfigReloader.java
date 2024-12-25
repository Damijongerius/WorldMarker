package com.dami.worldmarker.ConfigReload;

import java.util.ArrayList;
import java.util.List;

public abstract class IConfigReloader {

    protected static final List<IConfigReloadListener> listeners = new ArrayList<>();

    IConfigReloader(){}

    public static void addListener(IConfigReloadListener listener){
        listeners.add(listener);
    }

    public static void removeListener(IConfigReloadListener listener){
        listeners.remove(listener);
    }
}

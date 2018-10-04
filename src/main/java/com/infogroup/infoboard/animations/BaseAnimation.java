package com.infogroup.infoboard.animations;

import java.util.HashMap;

public abstract class BaseAnimation {


    public BaseAnimation() {

    }

    public abstract String next();

    protected abstract void loadSettings(HashMap<String, String> settings);

    public abstract Integer getRow();

    public abstract String name();

    public abstract Integer getInterval();
}

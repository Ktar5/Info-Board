package com.infogroup.infoboard.animations;

import java.util.HashMap;

public abstract class BaseAnimation {


    public BaseAnimation() {

    }

    public abstract String next();

    public abstract Integer getRow();

    public abstract String name();

    protected abstract void loadSettings(HashMap<String, String> settings);

}

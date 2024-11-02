package com.runemate.fenrisfeng.bonerunner;

import com.runemate.fenrisfeng.bonerunner.branches.*;
import com.runemate.fenrisfeng.bonerunner.commons.*;
import com.runemate.fenrisfeng.bonerunner.settings.*;
import com.runemate.game.api.script.framework.tree.*;
import com.runemate.ui.setting.annotation.open.*;

public class BoneRunner extends TreeBot  {
    private final CheckIfInPOH rootTask = new CheckIfInPOH();

    @SettingsProvider(updatable = true)
    public BonesSettings bonesSettings;

    @SettingsProvider(updatable = true)
    public PlayerSettings playerSettings;

    private boolean started = false;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(final boolean started) {
        this.resume();
        this.started = started;
    }

    @Override
    public void onStart(final String... arguments) {
        this.getEventDispatcher().addListener(new CustomSettingsListener());
         CustomPlayerSense.initializeKeys();
    }

    @Override
    public TreeTask createRootTask() {
        return rootTask;
    }
}

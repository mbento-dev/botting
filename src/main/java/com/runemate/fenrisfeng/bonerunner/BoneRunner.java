package com.runemate.fenrisfeng.bonerunner;

import com.runemate.fenrisfeng.bonerunner.branches.*;
import com.runemate.fenrisfeng.bonerunner.settings.*;
import com.runemate.game.api.script.framework.listeners.*;
import com.runemate.game.api.script.framework.listeners.events.*;
import com.runemate.game.api.script.framework.tree.*;
import com.runemate.ui.setting.annotation.open.*;

public class BoneRunner extends TreeBot implements SettingsListener {
    private CheckIfInPOH rootTask = new CheckIfInPOH();


    @SettingsProvider(updatable = true)
    public BonesSettings bonesSettings;

    @SettingsProvider(updatable = true)
    public PlayerSettings playerSettings;

    @Override
    public TreeTask createRootTask() {
        return rootTask;
    }

    @Override
    public void onSettingChanged(final SettingChangedEvent settingChangedEvent) {
        this.resume();
    }

    @Override
    public void onSettingsConfirmed() {
        rootTask.setStarted(true);
    }
}

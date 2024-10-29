package com.runemate.fenrisfeng.bonerunner;

import com.runemate.fenrisfeng.bonerunner.branches.*;
import com.runemate.fenrisfeng.bonerunner.settings.*;
import com.runemate.game.api.script.framework.tree.*;
import com.runemate.ui.setting.annotation.open.*;

public class BoneRunner extends TreeBot {

    @SettingsProvider(updatable = true)
    public BonesSettings bonesSettings;

    @SettingsProvider(updatable = true)
    public PlayerSettings playerSettings;

    @Override
    public TreeTask createRootTask() {
        return new CheckIfInPOH();
    }
}

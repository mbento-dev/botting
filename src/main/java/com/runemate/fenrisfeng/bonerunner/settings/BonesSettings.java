package com.runemate.fenrisfeng.bonerunner.settings;

import com.runemate.ui.setting.annotation.open.*;
import com.runemate.ui.setting.open.Settings;

@SettingsGroup
public interface BonesSettings extends Settings {

    @Setting(key = "boneType", title = "Bones to trade", description = "Bones intended to trade and run")
    default String boneType() {
        return "Dragon bones";
    }

    @Range(min = 1, max = 27)
    @Setting(key = "bonesAmount", title = "Amount of bones per trade")
    default int bonesAmount() {
        return 26;
    }
}

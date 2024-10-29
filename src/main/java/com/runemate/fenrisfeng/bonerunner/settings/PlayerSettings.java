package com.runemate.fenrisfeng.bonerunner.settings;

import com.runemate.ui.setting.annotation.open.*;
import com.runemate.ui.setting.open.*;

@SettingsGroup
public interface PlayerSettings extends Settings {

    @Setting(key = "tradingPartnerName", title = "Trading partner", description = "Player intended to run for")
    default String tradingPartnerName() {
        return "";
    }
}

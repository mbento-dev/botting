package com.runemate.fenrisfeng.nagua.resources;

import com.runemate.ui.setting.annotation.open.*;
import com.runemate.ui.setting.open.*;

@SettingsGroup
public interface LocalSettings extends Settings {
    @Range(
        min = 0,
        max = 94
    )
    @Setting(
        key = "minPrayer",
        title = "Min Prayer",
        description = "Minimum prayer before potting"
    )
    default int minPrayer() {
        return 15;
    }
}

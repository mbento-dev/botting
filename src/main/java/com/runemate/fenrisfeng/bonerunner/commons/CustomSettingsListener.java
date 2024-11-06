package com.runemate.fenrisfeng.bonerunner.commons;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.script.framework.listeners.*;
import com.runemate.game.api.script.framework.listeners.events.*;
import org.apache.logging.log4j.*;

public class CustomSettingsListener implements SettingsListener {

    private static final Logger log = LogManager.getLogger(CustomSettingsListener.class);
    private BoneRunner bot = (BoneRunner) Environment.getBot();

    @Override
    public void onSettingChanged(final SettingChangedEvent settingChangedEvent) {
    }

    @Override
    public void onSettingsConfirmed() {
        bot = (BoneRunner) Environment.getBot();
        while (bot == null){
            log.info(bot);
        }

        bot.setStarted(true);
    }
}

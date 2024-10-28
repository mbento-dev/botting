package com.runemate.fenrisfeng.nagua;

import com.runemate.fenrisfeng.nagua.resources.*;
import com.runemate.fenrisfeng.nagua.tasks.*;
import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;
import com.runemate.ui.setting.annotation.open.*;
import org.apache.logging.log4j.*;

public class NaguaFarmer extends TaskBot {
    private static final Logger logger = LogManager.getLogger(NaguaFarmer.class);

    @SettingsProvider(updatable = true)
    private LocalSettings settings;

    @Override
    public void onStart(final String... arguments) {
        Player me = Players.getLocal();

        add(
            new DrinkPotion(logger, 15),
            new MakePotion(me, 2),
            new FindNaguaSpot(logger, me),
            new PickLoot(logger, me)
        );
    }
}

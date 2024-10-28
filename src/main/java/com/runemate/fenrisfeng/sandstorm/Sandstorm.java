package com.runemate.fenrisfeng.sandstorm;

import com.runemate.fenrisfeng.nagua.*;
import com.runemate.fenrisfeng.nagua.tasks.*;
import com.runemate.fenrisfeng.sandstorm.tasks.*;
import com.runemate.game.api.script.framework.task.*;
import org.apache.logging.log4j.*;

public class Sandstorm extends TaskBot {
    private static final Logger logger = LogManager.getLogger(Sandstorm.class);

    @Override
    public void onStart(final String... arguments) {
        add(
            new MineRock(logger),
            new DepositSandstone(logger)
        );
    }
}

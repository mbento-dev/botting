package com.runemate.fenrisfeng.hunleffmate;

import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.fenrisfeng.hunleffmate.tasks.*;
import com.runemate.fenrisfeng.nagua.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;
import org.apache.logging.log4j.*;

public class HunleffMate extends TaskBot {
    private static final Logger logger = LogManager.getLogger(NaguaFarmer.class);
    private static final FileLogger fileLogger = new FileLogger(logger);


    @Override
    public void onStart(final String... arguments) {
        Actor me = Players.getLocal();

        add(new ChangeWeapon(fileLogger, me));
    }
}

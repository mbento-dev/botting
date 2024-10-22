package com.runemate.fenrisfeng.hunleffmate;

import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.fenrisfeng.hunleffmate.events.*;
import com.runemate.fenrisfeng.hunleffmate.tasks.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;
import org.apache.logging.log4j.*;

public class HunleffMate extends TaskBot {
    private static final Logger logger = LogManager.getLogger(HunleffMate.class);
    private static final FileLogger fileLogger = new FileLogger(logger);



    @Override
    public void onStart(final String... arguments) {
        Actor me = Players.getLocal();
        System.out.println("java version: "+System.getProperty("java.version"));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));

        fileLogger.info("Starting");
        HunleffListener hunleffListener = new HunleffListener(fileLogger);
        fileLogger.info("Started");

        getEventDispatcher().addListener(hunleffListener);

//        add(new ChangeWeapon(fileLogger, me));
    }
}

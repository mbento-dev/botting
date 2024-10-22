package com.runemate.fenrisfeng.hunleffmate.events;

import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.fenrisfeng.hunleffmate.resources.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.osrs.local.hud.interfaces.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.listeners.*;
import com.runemate.game.api.script.framework.listeners.events.*;
import java.util.*;

public class HunleffListener implements NpcListener {
    private final FileLogger fileLogger;

    private String prayerName = "MAGIC";

    public HunleffListener(final FileLogger fileLogger) {
        this.fileLogger = fileLogger;
    }

    private void switchPrayer(String prayer) {
        final InterfaceComponent ICPrayer = switch (prayer){
            case "RANGED":
                yield Prayer.PROTECT_FROM_MISSILES.getComponent();
            case "MAGIC":
                yield Prayer.PROTECT_FROM_MAGIC.getComponent();
            default:
                throw new IllegalStateException("Unexpected value: " + prayer);
        };

        // Test if delay acts as a wait
        Execution.delay(33333,66666);
        DirectInput.send(MenuAction.forInterfaceComponent(ICPrayer, 0));
    }

    @Override
    public void onNpcAnimationChanged(final AnimationEvent event) {
        fileLogger.info(event.toString());
        if( event.getAnimationId() == InterestingAnimations.HUNLEFF.ATTACK_CHANGE_TO_MAGE) {
            switchPrayer("MAGIC");
        }
        if( event.getAnimationId() == InterestingAnimations.HUNLEFF.ATTACK_CHANGE_TO_RANGED) {
            switchPrayer("RANGED");
        }
    }
}

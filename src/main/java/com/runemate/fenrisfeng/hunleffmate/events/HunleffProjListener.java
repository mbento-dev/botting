package com.runemate.fenrisfeng.hunleffmate.events;

import com.runemate.fenrisfeng.hunleffmate.resources.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.osrs.local.hud.interfaces.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.listeners.*;
import com.runemate.game.api.script.framework.listeners.events.*;

@Deprecated // POC
public class HunleffProjListener implements ProjectileListener {
    private static class HunleffProj {
        final static int HUNLEFF_RANGED = 1;
        final static int HUNLEFF_MAGE = 2;
    }

    private int rangedCounter = 0;
    private int mageCounter = 0;

    private void switchPrayer(String prayer) {
        final InterfaceComponent ICPrayer = switch (prayer){
            case "MISSILES":
                mageCounter = 0;
                yield Prayer.PROTECT_FROM_MISSILES.getComponent();
            case "MAGIC":
                rangedCounter = 0;
                yield Prayer.PROTECT_FROM_MAGIC.getComponent();
            default:
                throw new IllegalStateException("Unexpected value: " + prayer);
        };

        Execution.delay(333,666);
        DirectInput.send(MenuAction.forInterfaceComponent(ICPrayer, 0));
    }

    @Override
    public void onProjectileLaunched(final ProjectileLaunchEvent event) {
        switch (event.getProjectile().getSpotAnimationId()) {
            case HunleffProj.HUNLEFF_RANGED:
                rangedCounter++;
                if ( rangedCounter == 4 ){
                    switchPrayer("MAGIC");
                    return;
                }
                break;
            case HunleffProj.HUNLEFF_MAGE:
                mageCounter++;
                if ( mageCounter == 4 ){
                    switchPrayer("MISSILES");
                    return;
                }
                break;
        }
    }
}

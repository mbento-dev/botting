package com.runemate.fenrisfeng.hunleffmate.events;

import com.runemate.fenrisfeng.hunleffmate.resources.*;
import com.runemate.game.api.hybrid.entities.details.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.osrs.local.hud.interfaces.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.listeners.*;
import com.runemate.game.api.script.framework.listeners.events.*;
import org.apache.logging.log4j.*;

public class HunleffListener implements NpcListener {
    private final Logger logger;

    public HunleffListener(final Logger logger) {
        this.logger = logger;
    }

    private void switchPrayer(String prayer, Animable anima) {
        final InterfaceComponent ICPrayer = switch (prayer){
            case "RANGED":
                yield Prayer.PROTECT_FROM_MISSILES.getComponent();
            case "MAGIC":
                yield Prayer.PROTECT_FROM_MAGIC.getComponent();
            default:
                throw new IllegalStateException("Unexpected value: " + prayer);
        };

        Execution.delayUntil(() -> anima.getAnimationId() != -1, 600);
        DirectInput.send(MenuAction.forInterfaceComponent(ICPrayer, 0));
    }

    @Override
    public void onNpcAnimationChanged(final AnimationEvent event) {
        if( event.getAnimationId() == InterestingAnimations.HUNLEFF.ATTACK_CHANGE_TO_MAGE) {
            switchPrayer("MAGIC", event.getSource());
        }
        if( event.getAnimationId() == InterestingAnimations.HUNLEFF.ATTACK_CHANGE_TO_RANGED) {
            switchPrayer("RANGED", event.getSource());
        }
    }
}

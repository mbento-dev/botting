package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.entities.status.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.osrs.local.hud.interfaces.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;

public class PrayMelee extends Task {
    private final Player me;

    public PrayMelee(final Player me) {
        this.me = me;
    }

    @Override
    public boolean validate() {
        if (Npcs.newQuery().targeting(me).results().isEmpty()){
            return false;
        }
        return !Prayer.getActivePrayers().contains(Prayer.PROTECT_FROM_MELEE);
    }

    @Override
    public void execute() {
        DirectInput.send(MenuAction.forInterfaceComponent(Prayer.PROTECT_FROM_MELEE.getComponent(), 0));
        Execution.delayUntil(() -> me.getOverheadIcons().get(0).getPrayerType() == OverheadIcon.PrayerType.MELEE, 600);
    }
}

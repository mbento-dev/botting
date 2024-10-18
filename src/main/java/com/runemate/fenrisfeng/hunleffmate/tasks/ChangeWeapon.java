package com.runemate.fenrisfeng.hunleffmate.tasks;

import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.entities.status.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.player_sense.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;

public class ChangeWeapon extends Task {
    private final FileLogger fileLogger;
    private final Actor me;

    private Npc hunleff;
    private SpriteItem weapon;
    private boolean swapToRanged;

    public ChangeWeapon(final FileLogger fileLogger, final Actor me) {
        this.fileLogger = fileLogger;
        this.me = me;
    }


    @Override
    public boolean validate() {
        hunleff = Npcs.newQuery().names("Corrupted hunleff").reachable().results().first();
        // Checks if hunleff is reachable
        if(hunleff == null) {
            return false;
        }

        OverheadIcon overhead = hunleff.getOverheadIcons().get(0);

//        weapon = Equipment.newQuery().names("Corrupted ((staff)|(bow)) \\(\\w*\\)").results().first();
        weapon = Equipment.newQuery().names("Corrupted staff \\(\\w*\\)").results().first();

        if (weapon == null && overhead.getPrayerType() == OverheadIcon.PrayerType.MAGIC) {
            swapToRanged = true;
            return true;
        }
        if (weapon != null && overhead.getPrayerType() == OverheadIcon.PrayerType.RANGED) {
            swapToRanged = false;
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        SpriteItem weapon;
        if (swapToRanged) {
            weapon = Equipment.newQuery().names("Corrupted bow \\(\\w*\\)").results().first();
        } else {
            weapon = Equipment.newQuery().names("Corrupted staff \\(\\w*\\)").results().first();
        }
        if (weapon == null) {
            return;
        }
        DirectInput.send(MenuAction.forSpriteItem(weapon, "Equip"));
        Execution.delay(600, 1200);
    }
}

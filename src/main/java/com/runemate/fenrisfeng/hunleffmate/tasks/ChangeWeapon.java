package com.runemate.fenrisfeng.hunleffmate.tasks;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.entities.status.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;
import org.apache.logging.log4j.*;

public class ChangeWeapon extends Task {
    private final Logger logger;
    private final Actor me;

    private Npc hunleff;
    private SpriteItem weapon;
    private boolean swapToRanged;

    public ChangeWeapon(final Logger logger, final Actor me) {
        this.logger = logger;
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
        GameObject tree;
        tree = GameObjects
            .newQuery()
            .within(new Area.Circular(Objects.requireNonNull(me.getPosition()), 10))
            .names("Dead tree")
            .actions("Chop down")
            .results()
        .nearestTo(me.getPosition());

        assert tree != null;
        DirectInput.send(MenuAction.forGameObject(tree, "Chop down"));

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

        if (me == null || me.getTarget() == null || me.getTarget().getName() == null ) {
            Execution.delay(300, 622);
            return;
        }
        Execution.delayUntil(() -> me.getTarget().getName().equals("Corrupted Hunleff"), 300);
    }
}

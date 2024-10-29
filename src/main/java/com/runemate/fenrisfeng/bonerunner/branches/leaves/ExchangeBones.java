package com.runemate.fenrisfeng.bonerunner.branches.leaves;

import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;
import lombok.*;

public class ExchangeBones extends LeafTask {
    @Setter
    private String boneType = "Dragon bones";

    @Override
    public void execute() {
        SpriteItem notedBones = Inventory
            .newQuery()
            .names(boneType)
            .noted()
            .results()
            .first();
        if (notedBones == null) {
            Environment.getBot().pause("No more " + boneType + " found.");
            return;
        }

        Npc phials = Npcs
            .newQuery()
            .names("Phials")
            .results()
            .first();
        if (phials == null) {
            return;
        }

        notedBones.click();
        Camera.concurrentlyTurnTo(phials);
        Execution.delayUntil(phials::isVisible);
        phials.click();
    }
}

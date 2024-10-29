package com.runemate.fenrisfeng.bonerunner.branches.leaves;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;
import java.util.*;

public class ExchangeBones extends LeafTask {
    private final BoneRunner bot = (BoneRunner) Objects.requireNonNull(Environment.getBot());

    @Override
    public void execute() {
        String boneType = bot.bonesSettings.boneType();

        SpriteItem notedBones = Inventory
            .newQuery()
            .names(boneType)
            .noted()
            .results()
            .first();
        if (notedBones == null) {
            bot.pause("No more " + boneType + " found.");
            return;
        }

        Npc phials = Npcs
            .newQuery()
            .names("Phials")
            .within(new Area.Rectangular(
                new Coordinate(2945, 3219,0),
                new Coordinate(2951,3210, 0)
            ))
            .results()
            .first();
        if (phials == null) {
            return;
        }

        notedBones.click();

        Camera.concurrentlyTurnTo(phials);
        Execution.delayUntil(phials::isVisible);

        phials.click();

        ChatDialog.Option dialogOpt = ChatDialog.getOption("Exchange All: \\d* coins");
        if (dialogOpt == null){
            return;
        }
        dialogOpt.select();
    }
}

package com.runemate.fenrisfeng.bonerunner.branches.leaves;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;

public class ExitPOH extends LeafTask {
    @Override
    public void execute() {
        GameObject portal = GameObjects
            .newQuery()
            .names("Portal")
            .actions("Enter")
            .results()
            .first();
        if (portal == null) {
            return;
        }

        Camera.concurrentlyTurnTo(portal);
        Execution.delayUntil(portal::isVisible, 6000);
        if (!portal.isVisible()) {
            return;
        }
        portal.interact("Enter");
    }
}

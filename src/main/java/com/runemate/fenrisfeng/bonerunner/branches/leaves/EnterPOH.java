package com.runemate.fenrisfeng.bonerunner.branches.leaves;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;

public class EnterPOH extends LeafTask {
    @Override
    public void execute() {
        GameObject houseAdvertisement = GameObjects
            .newQuery()
            .names("House Advertisement")
            .actions("Visit-Last")
            .reachable()
            .results()
            .first();
        if (houseAdvertisement == null) {
            return;
        }

        houseAdvertisement.interact("Visit-Last");
        Execution.delayUntil(House::isInside, 6000, 12000);
    }
}

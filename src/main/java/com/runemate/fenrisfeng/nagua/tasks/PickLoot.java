package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.nagua.resources.*;
import com.runemate.fenrisfeng.training.logger.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;

public class PickLoot extends Task {
    private final FileLogger fileLogger;
    private final Player me;
    private GroundItem nearestItem;

    public PickLoot(final FileLogger fileLogger, final Player me) {
        this.fileLogger = fileLogger;
        this.me = me;
    }

    @Override
    public boolean validate() {
        if(me.getTarget() != null)
            return false;

        nearestItem = GroundItems
            .newQuery()
            .names(InterestingLoot.CLUE.getAllNamesRegex())
            .mine()
            .names(InterestingLoot.CLUE.getAllNamesRegex())
            .results()
            .nearest();

        return nearestItem != null;
    }

    @Override
    public void execute() {
        fileLogger.info("Picking item from the ground" + nearestItem);
//        nearestItem.interact("Pick");
    }
}

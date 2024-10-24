package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.nagua.resources.*;
import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;
import org.apache.logging.log4j.*;

public class PickLoot extends Task {
    private final Logger logger;
    private final Player me;
    private GroundItem nearestItem;

    public PickLoot(final Logger logger, final Player me) {
        this.logger = logger;
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
        logger.info("Picking item from the ground" + nearestItem);
        DirectInput.send(MenuAction.forGroundItem(nearestItem, "Pick"));
    }
}

package com.runemate.fenrisfeng.sandstorm.tasks;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import org.apache.logging.log4j.*;

public class MineRock extends Task {
    private final Logger logger;
    private Player player;

    Area sandstoneArea = new Area.Rectangular(
        new Coordinate(3164,2905,0),
        new Coordinate(3166,2906,0)
    );

    public MineRock(final Logger logger) {
        this.logger = logger;
        this.player = Players.getLocal();
    }

    @Override
    public boolean validate() {
        if (Inventory.isFull()) {
            return false;
        }
        if (player == null) {
            player = Players.getLocal();
            return false;
        }
        return player.getAnimationId() == -1;
    }

    @Override
    public void execute() {
        logger.info(player.getAnimationId());
        GameObject sandstoneToMine = GameObjects
            .newQuery()
            .names("Sandstone rocks")
            .within(sandstoneArea)
            .results()
            .nearest();
        if (sandstoneToMine != null) {
            sandstoneToMine.click();
            Execution.delayUntil(() -> player.getAnimationId() == 55);
        }
    }
}

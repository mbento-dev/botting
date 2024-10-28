package com.runemate.fenrisfeng.sandstorm.tasks;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.local.hud.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import org.apache.logging.log4j.*;

public class DepositSandstone extends Task {
    private final Logger logger;

    public DepositSandstone(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public void execute() {
        GameObject grinder = GameObjects
            .newQuery()
            .names("Grinder")
            .actions("Deposit")
            .within(new Area.Rectangular(
                new Coordinate(3150, 2907, 0),
                new Coordinate(3151, 2910, 0)
            ))
            .results()
            .first();
        logger.info("Grinder: " + grinder);

        if (grinder != null) {
            DirectInput.send(MenuAction.forGameObject(grinder, "Deposit"));
            Execution.delayUntil(() -> !Inventory.isFull());
        }
    }
}

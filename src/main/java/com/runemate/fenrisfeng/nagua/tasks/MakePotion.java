package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.script.framework.task.*;

public class MakePotion extends Task {
    private final int potLimit;

    public MakePotion(final int potLimit) {
        this.potLimit = potLimit;
    }

    @Override
    public boolean validate() {
        return Inventory.newQuery().names("Moonlight potion\\([1-4]\\)").results().size() < potLimit;
    }

    @Override
    public void execute() {
        // Go to collection area
        // Grab X amount of grubs where X is the number of inv slots empty - 2
        // Run to blood moon area
        // Check for pestle and mortar
        // Crush the grubs
        // Grab 2 vials of water
        // Make 2 potion
        // Repeat until out of grubs
        // Drop remaining vials of water
    }
}

package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.common.*;
import com.runemate.fenrisfeng.nagua.resources.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.navigation.*;
import com.runemate.game.api.hybrid.location.navigation.cognizant.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;

public class MakePotion extends Task {
    private final Player me;
    private final int potLimit;

    public MakePotion(final Player me,final int potLimit) {
        this.me = me;
        this.potLimit = potLimit;
    }

    @Override
    public boolean validate() {
        return Inventory.newQuery().names("Moonlight potion\\([1-4]\\)").results().size() < potLimit;
    }

    @Override
    public void execute() {
        Area destination = InterestingArea.BLOOD_MOON_GATE.getArea();
        Pathing.getDestinationFromMe(destination, me);
        GameObjects.newQuery().names("Gate").actions("Enter");
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

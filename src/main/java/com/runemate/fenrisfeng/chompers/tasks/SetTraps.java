package com.runemate.fenrisfeng.chompers.tasks;

import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;

public class SetTraps extends Task {
    @Override
    public boolean validate() {
        if (GameObjects.newQuery().names("Trap").results().size() >= 4) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        // Find a spot not filled and fill it.
        ArrayList<Coordinate> coordinateList = new ArrayList<Coordinate>();
        GameObjects
            .newQuery()
            .names("Trap")
            .results()
            .forEach(
                (gameObject) -> coordinateList.add(gameObject.getPosition())
            );
        coordinateList.forEach((coordinate) -> {
            // check if coordinate is desired for a trap
        });
    }
}

package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.fenrisfeng.nagua.resources.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.entities.status.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;
import lombok.*;

public class FindNaguaSpot extends Task {
    private final FileLogger fileLogger;
    private final Player me;
    private final InterestingArea naguaTemple = InterestingArea.NAGUA_TEMPLE;
    private final InterestingArea naguaStove = InterestingArea.NAGUA_STOVE;
    private final InterestingArea naguaChest = InterestingArea.NAGUA_CHEST;

    @Setter
    @Getter
    private String zone;

    public FindNaguaSpot(final FileLogger fileLogger, final Player me) {
        this.fileLogger = fileLogger;
        this.me = me;
    }

    private InterestingArea getAreaByZone(String zone) {
        return switch (zone) {
            case "NAGUA_TEMPLE" -> naguaTemple;
            case "NAGUA_STOVE" -> naguaStove;
            case "NAGUA_CHEST" -> naguaChest;
            default -> null;
        };
    }

    @Override
    public boolean validate() {
        InterestingArea area = getAreaByZone(zone);
        assert area != null;
        Players
            .newQuery()
            .within(area.getArea())
            .visible()
            .names("\\^\\(" + me.getName() + "\\)")
            .overheadIcons()
            .results();

        // Search for nagua around the area
        // Find if there are players around the area
        // Find if out of combat
        fileLogger.info("I'm here: " + me.getPosition());

        return false;
    }

    @Override
    public void execute() {

    }
}

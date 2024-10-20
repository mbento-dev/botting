package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.fenrisfeng.nagua.resources.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.location.navigation.*;
import com.runemate.game.api.hybrid.location.navigation.cognizant.*;
import com.runemate.game.api.hybrid.queries.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;
import lombok.*;

public class FindNaguaSpot extends Task {
    private final FileLogger fileLogger;
    private final Player me;
    private final interestingArea naguaTemple = interestingArea.NAGUA_TEMPLE;
    private final interestingArea naguaStove = interestingArea.NAGUA_STOVE;
    private final interestingArea naguaChest = interestingArea.NAGUA_CHEST;
    private final String[] zones = {"NAGUA_TEMPLE", "NAGUA_STOVE", "NAGUA_CHEST"};

    @Setter
    @Getter
    private String zone;
    private int zoneCounter = 0;
    private boolean hop = false;
    private int currentWorld;

    private final WorldType[] excludeWTArray = {
        WorldType.PVP,
        WorldType.PVP_ARENA,
        WorldType.BOUNTY,
        WorldType.QUEST_SPEEDRUNNING,
        WorldType.LAST_MAN_STANDING,
        WorldType.NOSAVE_MODE,
        WorldType.TOURNAMENT_WORLD,
        WorldType.FRESH_START_WORLD,
        WorldType.DEADMAN,
        WorldType.SEASONAL,
        WorldType.HIGH_RISK,
        WorldType.SKILL_TOTAL,
        WorldType.BETA,
        WorldType.SUPER_SECRET
    };

    private final List<WorldType> excludeWT = Arrays.asList(excludeWTArray);

    public FindNaguaSpot(final FileLogger fileLogger, final Player me) {
        this.fileLogger = fileLogger;
        this.me = me;
    }

    private void searchNextZone() {
        if (zoneCounter >= 3) {
            zoneCounter = 0;
            currentWorld = Worlds.getCurrent();
            hop = true;
        }
        zoneCounter++;
        hop = false;
    }

    private void hopWorld() {
        WorldHop.hopToFirst((world) -> world.getWorldTypes().stream().anyMatch(excludeWT::contains));
    }

    private interestingArea getAreaByZone(String zone) {
        return switch (zone) {
            case "NAGUA_TEMPLE" -> naguaTemple;
            case "NAGUA_STOVE" -> naguaStove;
            default -> naguaChest;
        };
    }

    @Override
    public boolean validate() {
        interestingArea area = getAreaByZone(zone);
        assert area != null;
        if ( Players
            .newQuery()
            .within(area.getArea())
            .visible()
            .names("\\^\\(" + me.getName() + "\\)")
            .overheadIcons()
            .results().last() == null ) {
            searchNextZone();
            return true;
        }

        // Search for nagua around the area
        // Find if there are players around the area
        // Find if out of combat
        return false;
    }

    @Override
    public void execute() {
        if (hop) {
            hop = false;
            hopWorld();
            Execution.delayUntil(() -> currentWorld == Worlds.getCurrent());
        } else {
            Area destination = getAreaByZone(zones[zoneCounter]).getArea();
            Path path = ScenePath.buildTo(destination);
            if (me.getPosition() == null) return;
            while (me.getPosition().distanceTo(destination) > 5) {
                if (path != null) {
                    path.step();
                }
            }
        }

    }
}

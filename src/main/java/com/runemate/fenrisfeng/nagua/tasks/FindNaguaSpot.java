package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.common.*;
import com.runemate.fenrisfeng.nagua.resources.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;
import lombok.*;
import org.apache.logging.log4j.*;

public class FindNaguaSpot extends Task {
    private final Logger logger;
    private final Player me;
    private final InterestingArea naguaTemple = InterestingArea.NAGUA_TEMPLE;
    private final InterestingArea naguaStove = InterestingArea.NAGUA_STOVE;
    private final InterestingArea naguaChest = InterestingArea.NAGUA_CHEST;
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

    public FindNaguaSpot(final Logger logger, final Player me) {
        this.logger = logger;
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

    private InterestingArea getAreaByZone(String zone) {
        return switch (zone) {
            case "NAGUA_TEMPLE" -> naguaTemple;
            case "NAGUA_STOVE" -> naguaStove;
            default -> naguaChest;
        };
    }

    @Override
    public boolean validate() {
        InterestingArea area = getAreaByZone(zone);
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
            Pathing.getDestinationFromMe(destination, me);
        }

    }
}

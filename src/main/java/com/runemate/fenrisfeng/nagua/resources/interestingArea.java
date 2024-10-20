package com.runemate.fenrisfeng.nagua.resources;

import com.runemate.game.api.hybrid.location.*;
import lombok.*;

public enum interestingArea {
    NAGUA_TEMPLE(
        new Area.Circular(new Coordinate(1376, 9561, 0), 10),
        "NAGUA_TEMPLE"
    ),
    NAGUA_CHEST(
        new Area.Circular(new Coordinate(1356, 9567, 0), 10),
        "NAGUA_CHEST"
    ),
    NAGUA_STOVE(
        new Area.Circular(new Coordinate(1356, 9554, 0), 10),
        "NAGUA_STOVE"
    ),

    BLOOD_MOON_STOVE(new Area.Circular(new Coordinate(1350, 9582, 0), 2)),
    BLOOD_MOON_GATE(new Area.Circular(new Coordinate(1347, 9591, 0), 5)),

    GRUBS(new Area.Circular(new Coordinate(1356, 9554, 0), 10));

    @Getter
    private final Area area;
    @Getter
    private String zone;

    interestingArea(Area area) {
        this.area = area;
    }

    interestingArea(Area area, String zone) {
        this.area = area;
        this.zone = zone;
    }
}

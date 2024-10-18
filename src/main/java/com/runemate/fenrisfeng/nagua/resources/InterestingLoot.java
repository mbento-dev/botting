package com.runemate.fenrisfeng.nagua.resources;

import java.util.*;
import lombok.*;

@Getter
public enum InterestingLoot {
    // Ores
    IRON_ORE("Iron ore"),
    COAL_ORE("Coal"),
    SILVER_ORE("Silver ore"),
    MITHRIL_ORE("Mithril ore"),
    // Runes
    CHAOS_RUNE("Chaos rune"),
    NATURE_RUNE("Nature rune"),
    DEATH_RUNE("Death rune"),
    // Gems
    UNCUT_GEMS("Uncut \\*"),
    // Special drops
    SULPHUR_BLADES("Sulphur blades"),
    SULPHUR_ESSENCE("Sulphur essence"),
    KEYS("\\w* half of key"),
    CLUE("Clue scroll (hard)");

    private final String name;

    InterestingLoot(String name) {
        this.name = name;
    }

    public String getAllNamesRegex() {
        List<String> names = EnumSet.allOf(InterestingLoot.class).stream().map(InterestingLoot::getName).toList();
        return String.join("|", names);
    }
}

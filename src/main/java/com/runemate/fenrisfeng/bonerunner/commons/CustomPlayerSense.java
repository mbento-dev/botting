package com.runemate.fenrisfeng.bonerunner.commons;

import com.runemate.game.api.hybrid.player_sense.*;
import com.runemate.game.api.hybrid.util.calculations.*;
import java.util.function.*;

public class CustomPlayerSense {
    public static void initializeKeys () {
        for (Key key: Key.values()) {
            if (PlayerSense.get(key.name) == null) {
                PlayerSense.put(key.name, key.supplier.get());
            }
        }
    }

    public enum Key {
        MIN_DELAY_FOR_TRADE("min_delay_for_trade",
            () -> Random.nextInt(10000, 15000)),
        MAX_DELAY_FOR_TRADE("min_delay_for_trade",
            () -> {
                int minDelay = (int) MIN_DELAY_FOR_TRADE.supplier.get();
                return Random.nextInt(minDelay + 5000, minDelay + 15000);
            });

        public final String name;
        public final Supplier supplier;


        Key(final String name, final Supplier supplier) {
            this.name = name;
            this.supplier = supplier;
        }

        public String getKey() {
            return name;
        }

        public Integer getAsInteger() {
            return PlayerSense.getAsInteger(name);
        }

        public Double getAsDouble() {
            return PlayerSense.getAsDouble(name);
        }

        public Long getAsLong() {
            return PlayerSense.getAsLong(name);
        }

        public Boolean getAsBoolean() {
            return PlayerSense.getAsBoolean(name);
        }
    }
}

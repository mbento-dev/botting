package com.runemate.fenrisfeng.common;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.location.navigation.*;
import com.runemate.game.api.hybrid.location.navigation.cognizant.*;
import com.runemate.game.api.script.*;

public class Pathing {
    public static void getDestinationFromMe(final Area destination, final Player me) {
        Path path = ScenePath.buildTo(destination);
        assert me.getPosition() != null;
        while (me.getPosition().distanceTo(destination) > 5) {
            assert path != null;
            path.step();
            Execution.delayUntil(() -> me.getPosition().distanceTo(path.getNext()) > 5, 3000);
        }
    }
}

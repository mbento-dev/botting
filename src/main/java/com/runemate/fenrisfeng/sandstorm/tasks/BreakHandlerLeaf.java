package com.runemate.fenrisfeng.sandstorm.tasks;

import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.osrs.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;
import com.runemate.game.events.osrs.*;
import java.time.*;
import lombok.*;

public class BreakHandlerLeaf extends LeafTask {
    @Setter
    private Instant timeToStop;

    @Override
    public void execute() {
        OSRSRunescape.logout();
        Execution.delayUntil(() -> Instant.now().isAfter(timeToStop));
    }
}

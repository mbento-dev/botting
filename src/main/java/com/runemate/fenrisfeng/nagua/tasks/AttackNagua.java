package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.training.logger.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import com.runemate.game.events.osrs.*;
import org.apache.logging.log4j.*;

public class AttackNagua extends Task {
    private final FileLogger fileLogger;
    private Actor me;
    private Npc nearestNagua;

    @Override
    public boolean validate() {
        if (me == null){
            me = Players.getLocal();
            return false;
        };

        nearestNagua = Npcs.newQuery().names("Sulphur Nagua").reachable().targeting(me).results().nearest();
        if (me.getTarget() == null) {
            return false;
        }

        return nearestNagua != null;
    }

    @Override
    public void execute() {
        if(nearestNagua.interact("Attack"))
            Execution.delay(600, 1200);
    }

    public AttackNagua(final FileLogger fileLogger, Actor me) {
        this.fileLogger = fileLogger;
        this.me = me;
    }
}

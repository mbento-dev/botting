package com.runemate.fenrisfeng.chompers.tasks;

import com.runemate.fenrisfeng.common.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;

public class MoveToArea  extends Task {
    private final Area priffChomper = new Area.Circular(new Coordinate(0,0,0), 30);
    private Player me;
    @Override
    public boolean validate() {
        me = Players.getLocal();
        assert me != null;
        if(
            me.getPosition() != null
            && me.getPosition().distanceTo(priffChomper) < 30
        ) {
            return false;
        }
        return priffChomper.isReachable();
    }

    @Override
    public void execute() {
        Pathing.getDestinationFromMe(priffChomper, me);
    }
}

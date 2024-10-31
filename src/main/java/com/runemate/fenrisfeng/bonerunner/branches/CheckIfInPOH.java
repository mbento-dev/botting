package com.runemate.fenrisfeng.bonerunner.branches;

import com.runemate.fenrisfeng.bonerunner.branches.leaves.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.script.framework.tree.*;
import lombok.*;

public class CheckIfInPOH extends BranchTask {
    public final TradeBones tradeBonesTask = new TradeBones();
    public final ExitPOH exitPOHTask = new ExitPOH();
    public final EnterPOH enterPOHTask = new EnterPOH();
    public final ExchangeBones exchangeBonesTask = new ExchangeBones();

    private final TreeTask onSuccess = new CheckForBones(
        tradeBonesTask,
        exitPOHTask
    );
    private final TreeTask onFailure = new CheckForBones(
        enterPOHTask,
        exchangeBonesTask
    );

    private boolean started = false;

    public void setStarted(final boolean started) {
        this.started = started;
    }

    @Override
    public boolean validate() {
        if (!started) return false;
        return House.isInside();
    }

    @Override
    public TreeTask successTask() {
        return onSuccess;
    }

    @Override
    public TreeTask failureTask() {
        return onFailure;
    }
}

package com.runemate.fenrisfeng.bonerunner.branches;

import com.runemate.fenrisfeng.bonerunner.branches.leaves.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.script.framework.tree.*;

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

    @Override
    public boolean validate() {
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

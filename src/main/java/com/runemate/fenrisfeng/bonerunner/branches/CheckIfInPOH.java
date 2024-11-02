package com.runemate.fenrisfeng.bonerunner.branches;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.fenrisfeng.bonerunner.branches.leaves.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.script.framework.tree.*;
import lombok.*;
import org.apache.logging.log4j.*;

public class CheckIfInPOH extends BranchTask {
    private static final Logger log = LogManager.getLogger(CheckIfInPOH.class);
    public final TradeBones tradeBonesTask = new TradeBones();
    public final ExitPOH exitPOHTask = new ExitPOH();
    public final EnterPOH enterPOHTask = new EnterPOH();
    public final ExchangeBones exchangeBonesTask = new ExchangeBones();

    private BoneRunner bot = (BoneRunner) Environment.getBot();

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
        bot = (BoneRunner) Environment.getBot();
        if (bot == null) {
            bot = (BoneRunner) Environment.getBot();
            log.info(bot);
            return false;
        }

        log.info("Bot started? {}", bot.isStarted());


        if (!bot.isStarted()) return false;
        log.info("Is inside a house: {}", House.isInside());
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

package com.runemate.fenrisfeng.bonerunner.branches;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.queries.results.*;
import com.runemate.game.api.script.framework.tree.*;
import java.util.*;
import org.apache.logging.log4j.*;

public class CheckForBones extends BranchTask {

    private static final Logger log = LogManager.getLogger(CheckForBones.class);
    private BoneRunner bot = (BoneRunner) Environment.getBot();

    private final TreeTask onSuccessTask;
    private final TreeTask onFailureTask;

    public CheckForBones(final TreeTask onSuccessTask, final TreeTask onFailureTask) {
        this.onSuccessTask = onSuccessTask;
        this.onFailureTask = onFailureTask;
    }

    @Override
    public boolean validate() {
        if (bot == null) {
            bot = (BoneRunner) Environment.getBot();
            log.info(bot);
            return false;
        }

        if (!bot.isStarted()) return false;

        String boneType = bot.bonesSettings.boneType();
        int bonesAmount = bot.bonesSettings.bonesAmount();

        SpriteItemQueryResults bones = Inventory
            .newQuery()
            .names(boneType)
            .unnoted()
            .results();
        return bones.size() >= bonesAmount;
    }

    @Override
    public TreeTask successTask() {
        return onSuccessTask;
    }

    @Override
    public TreeTask failureTask() {
        return onFailureTask;
    }
}

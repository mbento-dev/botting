package com.runemate.fenrisfeng.bonerunner.branches;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.queries.results.*;
import com.runemate.game.api.script.framework.tree.*;
import java.util.*;

public class CheckForBones extends BranchTask {
    private final BoneRunner bot = (BoneRunner) Objects.requireNonNull(Environment.getBot());

    private final TreeTask onSuccessTask;
    private final TreeTask onFailureTask;

    public CheckForBones(final TreeTask onSuccessTask, final TreeTask onFailureTask) {
        this.onSuccessTask = onSuccessTask;
        this.onFailureTask = onFailureTask;
    }

    @Override
    public boolean validate() {
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

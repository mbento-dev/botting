package com.runemate.fenrisfeng.bonerunner.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.queries.results.*;
import com.runemate.game.api.script.framework.tree.*;
import lombok.*;

public class CheckForBones extends BranchTask {
    @Setter
    private String boneType = "Dragon bones";
    @Setter
    private int inventorySize = 27;

    private final TreeTask onSuccessTask;
    private final TreeTask onFailureTask;

    public CheckForBones(final TreeTask onSuccessTask, final TreeTask onFailureTask) {
        this.onSuccessTask = onSuccessTask;
        this.onFailureTask = onFailureTask;
    }

    @Override
    public boolean validate() {
        SpriteItemQueryResults bones = Inventory
            .newQuery()
            .names(boneType)
            .unnoted()
            .results();
        return bones.size() >= inventorySize;
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

package com.runemate.fenrisfeng.sandstorm.tasks;

import com.runemate.game.api.script.framework.tree.*;
import java.time.*;
import java.util.*;
import java.util.random.*;
import lombok.*;
import org.jetbrains.annotations.*;

public class BreakHandler extends BranchTask {
    private final TreeTask onSuccessTask;
    private final Random rand = new Random();

    private Instant lastBreak;
    private Instant nextBreak;
    private int minMinutes;
    private int maxMinutes;
    // breakDuration must be in seconds
    private int breakDuration;
    private int minDuration;
    private int maxDuration;

    @Setter
    private boolean isBreaking = false;

    public void resetBreak () {
        lastBreak = Instant.now();
        this.nextBreak = Instant.now()
            .plusSeconds((rand.nextInt(maxMinutes - minMinutes + 1) + minMinutes) * 60L);
        this.breakDuration = rand.nextInt(maxDuration - minDuration + 1) + minDuration;
    }

    public BreakHandler(@NotNull TreeTask onSuccess, int minMinutes, int maxMinutes, int minDuration, int maxDuration) {
        this.onSuccessTask = onSuccess;
        this.lastBreak = Instant.now();
        this.nextBreak = Instant.now()
            .plusSeconds(rand.nextInt(minMinutes, maxMinutes + 1) * 60L);
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
    }

    @Override
    public boolean validate() {
        return Instant.now().isBefore(nextBreak) && !isBreaking;
    }

    @Override
    public TreeTask successTask() {
        return onSuccessTask;
    }

    private BreakHandlerLeaf breakHandlerLeaf = new BreakHandlerLeaf();

    @Override
    public TreeTask failureTask() {
        this.resetBreak();
        breakHandlerLeaf.setTimeToStop(Instant.now().plusSeconds(breakDuration));
        return breakHandlerLeaf;
    }
}

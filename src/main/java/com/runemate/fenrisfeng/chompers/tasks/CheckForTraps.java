package com.runemate.fenrisfeng.chompers.tasks;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.input.direct.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;

public class CheckForTraps extends Task {
    GameObject go;
    @Override
    public boolean validate() {
        go = GameObjects.newQuery().names("Trap").actions("Reset").results().random();
        return go != null;
    }

    @Override
    public void execute() {
        // Remove broken trap
        DirectInput.send(MenuAction.forGameObject(go, "Reset"));
    }
}

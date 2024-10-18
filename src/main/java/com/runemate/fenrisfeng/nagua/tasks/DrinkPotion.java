package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.common.logger.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.osrs.local.hud.interfaces.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;
import java.util.regex.*;

public class DrinkPotion extends Task {
    private final FileLogger fileLogger;
    private final Random rand = new Random();

    private int randomMinPrayer = 15;
    private final int minPrayer;

    private SpriteItem potion;

    public DrinkPotion(FileLogger fileLogger, final int minPrayer) {
        this.fileLogger = fileLogger;
        this.minPrayer = minPrayer;
    }

    @Override
    public boolean validate() {
        int currentPrayer = Prayer
            .getPoints();
        if(currentPrayer > randomMinPrayer) {
            return false;
        }
        potion = Inventory
            .newQuery()
            .names(Pattern.compile("Moonlight potion\\([1-4]\\)"))
            .results()
            .sortByIndex()
            .last();
        return potion != null;
    }

    @Override
    public void execute() {
        randomMinPrayer = rand.nextInt(
            minPrayer - 5 ,
            minPrayer + 5
        );
        
        fileLogger.info("Drinking potion" + potion);
        if(potion.interact("Drink"))
            Execution.delay(600, 1200);
    }
}

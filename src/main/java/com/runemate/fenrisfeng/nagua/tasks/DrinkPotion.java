package com.runemate.fenrisfeng.nagua.tasks;

import com.runemate.fenrisfeng.training.logger.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.player_sense.*;
import com.runemate.game.api.osrs.local.hud.interfaces.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.task.*;
import java.util.*;
import java.util.random.*;
import java.util.regex.*;

public class DrinkPotion extends Task {
    private final FileLogger fileLogger;
    private final Random rand = new Random();

    private int randomMinPrayer = 15;
    private final int minPrayer;
    private int currentPrayer = 0;

    private SpriteItem potion;

    public DrinkPotion(FileLogger fileLogger, final int minPrayer) {
        this.fileLogger = fileLogger;
        this.minPrayer = minPrayer;
    }

    @Override
    public boolean validate() {
        currentPrayer = Prayer
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

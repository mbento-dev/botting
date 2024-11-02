package com.runemate.fenrisfeng.bonerunner.branches.leaves;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.input.*;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.*;
import com.runemate.game.api.hybrid.player_sense.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import org.apache.logging.log4j.*;

public class ExchangeBones extends LeafTask {

    private static final Logger log = LogManager.getLogger(ExchangeBones.class);
    private BoneRunner bot = (BoneRunner) Environment.getBot();

    @Override
    public void execute() {
        if (bot == null) {
            bot = (BoneRunner) Environment.getBot();
            log.info(bot);
            return;
        }

        if (!bot.isStarted()) {
            return;
        }

        String boneType = bot.bonesSettings.boneType();

        SpriteItem notedBones = Inventory
            .newQuery()
            .names(boneType)
            .noted()
            .results()
            .first();
        if (notedBones == null) {
            bot.pause("No more " + boneType + " found.");
            return;
        }

        Npc phials = Npcs
            .newQuery()
            .names("Phials")
            .within(new Area.Rectangular(
                new Coordinate(2945, 3219,0),
                new Coordinate(2951,3210, 0)
            ))
            .results()
            .first();
        if (phials == null) {
            return;
        }

        notedBones.click();

        Camera.concurrentlyTurnTo(phials);
        Execution.delayUntil(phials::isVisible);

        phials.click();

        log.info("Waiting for dialog to open");
        Execution.delayUntil(ChatDialog::isOpen, 6000, 12000);

        ChatDialog.Option dialogOpt = ChatDialog.getOption(Pattern.compile("Exchange All: \\d* coins"));
        if (dialogOpt == null){

            log.info("Dialog options: {}", ChatDialog.getOptions());
            return;
        }
        if (PlayerSense.getAsBoolean(PlayerSense.Key.USE_MISC_HOTKEYS)) {
            Keyboard.typeKey(48 + dialogOpt.getNumber());
        } else {
            dialogOpt.select();
        }
        Execution.delayUntil(() -> Inventory.newQuery().names(boneType).unnoted().results().first() != null);
    }
}

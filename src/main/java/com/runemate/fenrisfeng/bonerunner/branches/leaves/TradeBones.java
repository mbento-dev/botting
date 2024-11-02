package com.runemate.fenrisfeng.bonerunner.branches.leaves;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import lombok.*;
import org.apache.logging.log4j.*;

public class TradeBones extends LeafTask {

    private static final Logger log = LogManager.getLogger(TradeBones.class);
    private BoneRunner bot = (BoneRunner) Environment.getBot();

    @Override
    public void execute() {
        if (bot == null) {
            bot = (BoneRunner) Environment.getBot();
            log.info(bot);
            return;
        }
        String boneType = bot.bonesSettings.boneType();
        int bonesAmount = bot.bonesSettings.bonesAmount();

        String tradingPartnerName = bot.playerSettings.tradingPartnerName();

        Player tradingPartner = Players.newQuery().names(tradingPartnerName).results().first();
        if (tradingPartner == null) {
            return;
        }

        tradingPartner.interact("Trade");
        Execution.delayUntil(Trade::isOpen, 9000, 12000);

        if (!Trade.isOpen()) {
            return;
        }

        SpriteItem notedBones = Inventory
            .newQuery()
            .names(boneType)
            .noted()
            .results()
            .first();

        Trade.offer(notedBones, bonesAmount);

        final AtomicReference<SpriteItem> incomingBones = new AtomicReference<>();
        final AtomicReference<SpriteItem> coins = new AtomicReference<>();



        Execution.delayUntil(() -> {
            incomingBones.set(Trade.Incoming.newQuery().names(boneType).noted().results().first());
            if (incomingBones.get() == null) return false;
            if (incomingBones.get().getQuantity() != bonesAmount ) return false;
            coins.set(Trade.Incoming.newQuery().names("coins").results().first());
            if (coins.get() == null) return false;
            return coins.get().getQuantity() == bonesAmount * 5;
            }, 10000, 15000
        );

        if (incomingBones.get() == null
            || incomingBones.get().getQuantity() != bonesAmount
            || coins.get() == null
            || coins.get().getQuantity() != bonesAmount * 5) {
            return;
        }

        Trade.accept();
        Execution.delayUntil(Trade::atConfirmationScreen, 10000, 15000);

        Trade.accept();
        Execution.delayUntil(Trade::isOpen, 10000, 15000);
    }
}

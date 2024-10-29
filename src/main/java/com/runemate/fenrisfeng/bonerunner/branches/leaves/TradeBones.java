package com.runemate.fenrisfeng.bonerunner.branches.leaves;

import com.runemate.fenrisfeng.bonerunner.*;
import com.runemate.game.api.hybrid.*;
import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.*;
import com.runemate.game.api.script.*;
import com.runemate.game.api.script.framework.tree.*;
import java.util.*;
import lombok.*;

public class TradeBones extends LeafTask {
    @NonNull private final BoneRunner bot = (BoneRunner) Objects.requireNonNull(Environment.getBot());

    @Override
    public void execute() {
        String boneType = bot.bonesSettings.boneType();
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

        Trade.offer(notedBones, 27);
        Execution.delay(333,666);

        Trade.accept();
        Execution.delayUntil(Trade::atConfirmationScreen);

        Trade.accept();
        Execution.delayUntil(Trade::isOpen);
    }
}

package me.mathrandom7910.fabricmullah.command.commands.balance;

import com.mojang.brigadier.CommandDispatcher;
import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class BalanceCommand implements ICommandable {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("bal")
                .executes(context -> {
                    PlayerEntity player = context.getSource().getPlayer();
                    player.sendMessage(Text.of("Current balance is: " + Fabricmullah.getBalance(player)), false);
                    return 0;
                }));
    }
}

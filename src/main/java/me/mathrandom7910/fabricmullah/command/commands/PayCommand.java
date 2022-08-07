package me.mathrandom7910.fabricmullah.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

public class PayCommand implements ICommandable {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("pay")
                .then(CommandManager.argument("player", EntityArgumentType.players())
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();
                                    EntitySelector p2p = context.getArgument("player", EntitySelector.class);
                                    List<ServerPlayerEntity> players = p2p.getPlayers(context.getSource());
                                    if(players.size() != 1 || players.get(0).equals(player)) {
                                        player.sendMessage(Text.of("Invalid player!"), false);
                                        return 1;
                                    }
                                    PlayerEntity payToPlayer = players.get(0);

                                    int amount = context.getArgument("amount", Integer.class);
                                    int balance = Fabricmullah.getBalance(player);

                                    Fabricmullah.LOG.info(payToPlayer.getGameProfile().getName() + " found player");
                                    if(balance < amount){
                                        player.sendMessage(Text.of("Too much money to pay! Current balance is: " + balance), false);
                                        return 1;
                                    }

                                    Fabricmullah.decrBalance(player, amount);
                                    Fabricmullah.incrBalance(payToPlayer, amount);

                                    player.sendMessage(Text.of("Paid " + payToPlayer.getGameProfile().getName() + " " + amount), false);
                                    payToPlayer.sendMessage(Text.of("Received " + amount + " from " + player.getGameProfile().getName()), false);
                                    Fabricmullah.updateTop(player);
                                    return 0;
                                }))));
    }
}

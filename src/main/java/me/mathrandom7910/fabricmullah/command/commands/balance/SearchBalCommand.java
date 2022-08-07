package me.mathrandom7910.fabricmullah.command.commands.balance;

import com.mojang.brigadier.CommandDispatcher;
import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

public class SearchBalCommand implements ICommandable {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("searchbal")
                .then(CommandManager.argument("player", EntityArgumentType.players())
                        .executes(context -> {
                            EntitySelector p2p = context.getArgument("player", EntitySelector.class);
                            List<ServerPlayerEntity> players = p2p.getPlayers(context.getSource());
                            if(players.size() == 0) {
                                context.getSource().sendError(Text.of("Invalid player!"));
                                return 1;
                            }

                            for(ServerPlayerEntity player : players) {
                                context.getSource().sendFeedback(Text.of("Balance of " + player.getGameProfile().getName() +  " is: " + Fabricmullah.getBalance(player)), false);
                            }

                           return 0;
                        })));
    }
}

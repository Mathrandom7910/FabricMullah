package me.mathrandom7910.fabricmullah.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import me.mathrandom7910.ConfigHandler.Config;
import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.ai.brain.task.BreedTask;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class OfflinePay implements ICommandable {

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("pay")
                .then(CommandManager.argument("player", StringArgumentType.word())
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();

                                    String offPlayerName = context.getArgument("player", String.class);
                                    String uuid = Fabricmullah.NAME_TO_UUID.get(offPlayerName);

                                    if(uuid == null) {
                                        player.sendMessage(Text.of("That player has not joined the server before!"), false);
                                        return 1;
                                    }

                                    for(PlayerEntity player1 : player.world.getPlayers()) {
                                        if(player1.getName().asString().equalsIgnoreCase(offPlayerName)) {
                                            player.sendMessage(Text.of("That player is currently online!"), false);
                                            return 1;
                                        }
                                    }

                                    Config config = Fabricmullah.HANDLER.addConfig(uuid);

                                    if(config == null) {
                                        player.sendMessage(Text.of("Error finding player!"), false);
                                        return 1;
                                    }

                                    int amount = context.getArgument("amount", Integer.class);

                                    config.set("money", String.valueOf(Integer.parseInt(config.get("money")) + amount));
                                    Fabricmullah.decrBalance(player, amount);
                                    player.sendMessage(Text.of("Your balance is: " + Fabricmullah.HANDLER.getConfig(player.getUuidAsString()).get("money")), false);
                                    return 0;
                                }))));
    }
}

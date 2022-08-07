package me.mathrandom7910.fabricmullah.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public interface ICommandable {
    void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated);
}

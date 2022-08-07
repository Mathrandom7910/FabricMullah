package me.mathrandom7910.fabricmullah.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import me.mathrandom7910.fabricmullah.misc.MoneyItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class DepositCommand implements ICommandable {

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("deposit")
                .then(CommandManager.literal("hand").executes(DepositCommand::runHand)));
        dispatcher.register(CommandManager.literal("deposit")
                .then(CommandManager.literal("inventory").executes(DepositCommand::runInv)));
    }

    public static int runHand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = context.getSource().getPlayer();
        ItemStack stack = player.getInventory().getMainHandStack();
        Item item = stack.getItem();
        if(item instanceof MoneyItem) {
            depStack(player, stack);

            sendBal(player);
        } else {
            player.sendMessage(Text.of("No money in hand!"), false);
        }
        return 0;
    }

    private static void sendBal(PlayerEntity player) {
        player.sendMessage(Text.of("Current balance: " + Fabricmullah.getBalance(player)), false);
        Fabricmullah.updateTop(player);
    }

    private static void depStack(PlayerEntity player, ItemStack stack) {
        Fabricmullah.incrBalance(player, stack.getCount() * ((MoneyItem) stack.getItem()).getItemCost());
        stack.setCount(0);
    }

    public static int runInv(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = context.getSource().getPlayer();
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if(stack.getItem() instanceof MoneyItem) {
                depStack(player, stack);
            }
        }
        sendBal(player);
        return 0;
    }
}

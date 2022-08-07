package me.mathrandom7910.fabricmullah.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import static me.mathrandom7910.fabricmullah.registry.registries.ItemRegistry.*;

import me.mathrandom7910.fabricmullah.misc.MoneyItem;
import me.mathrandom7910.fabricmullah.registry.registries.ItemRegistry;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Arrays;


public class WithdrawCommand implements ICommandable {

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("withdraw")
                .then(CommandManager.argument("amount", IntegerArgumentType.integer(1))
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                                int amount = context.getArgument("amount", Integer.class);
                                int balance = Fabricmullah.getBalance(player);
                                if(amount > balance) {
                                    player.sendMessage(Text.of("Too much money to withdraw! Current balance is: " + balance), false);
                                    return 1;
                                }

                                for(int i = 0; i < amount; i++) {
//                                    boolean mfound = false;
//                                    for(MoneyItem moneyItem : MONEY_ITEMS) {
//                                        if(amount >= moneyItem.getItemCost() && amount - i >= moneyItem.getItemCost()) {
//                                            spawnItemEntity(new ItemStack(moneyItem, 1), player);
//                                            i += moneyItem.getItemCost() - 1;
//                                            Fabricmullah.decrBalance(player, moneyItem);
//                                            mfound = true;
//                                            break;
//                                        }
//                                    }
//                                    if(mfound) continue;

                                    if(amount >= THOUSAND_DOLLAR_ITEM.getItemCost() && amount - i >= THOUSAND_DOLLAR_ITEM.getItemCost()) {
                                        spawnItemEntity(new ItemStack(THOUSAND_DOLLAR_ITEM, 1), player);
                                        i += THOUSAND_DOLLAR_ITEM.getItemCost() - 1;
                                        Fabricmullah.decrBalance(player, THOUSAND_DOLLAR_ITEM.getItemCost());
                                        continue;
                                    }

                                    if(amount >= HUNDRED_DOLLAR_ITEM.getItemCost() && amount - i >= HUNDRED_DOLLAR_ITEM.getItemCost()) {
                                        spawnItemEntity(new ItemStack(HUNDRED_DOLLAR_ITEM, 1), player);
                                        i += HUNDRED_DOLLAR_ITEM.getItemCost() - 1;
                                        Fabricmullah.decrBalance(player, HUNDRED_DOLLAR_ITEM.getItemCost());
                                        continue;
                                    }

                                    if(amount >= TEN_DOLLAR_ITEM.getItemCost() && amount - i >= TEN_DOLLAR_ITEM.getItemCost()) {
                                        spawnItemEntity(new ItemStack(TEN_DOLLAR_ITEM, 1), player);
                                        i += TEN_DOLLAR_ITEM.getItemCost() - 1;
                                        Fabricmullah.decrBalance(player, TEN_DOLLAR_ITEM.getItemCost());
                                        continue;
                                    }

                                    if(amount >= DOLLAR_ITEM.getItemCost() && amount - i >= DOLLAR_ITEM.getItemCost()) {
                                        spawnItemEntity(new ItemStack(DOLLAR_ITEM, 1), player);
                                        i += DOLLAR_ITEM.getItemCost() - 1;
                                        Fabricmullah.decrBalance(player, DOLLAR_ITEM.getItemCost());
                                        continue;
                                    }

                                    spawnItemEntity(new ItemStack(PENNY_ITEM, 1), player);
                                    Fabricmullah.decrBalance(player, PENNY_ITEM.getItemCost());
                                }
                            player.sendMessage(Text.of("Withdrew " + amount + ", current balance is " + Fabricmullah.getBalance(player)), false);
                            Fabricmullah.updateTop(player);
                            return 0;
                        })));
    }

    private static void spawnItemEntity(ItemStack stack, PlayerEntity player) {
        player.getEntityWorld().spawnEntity(new ItemEntity(player.world, player.getX(), player.getY(), player.getZ(), stack));
    }


    private void giveBelow1(float amount, PlayerEntity player) {
       /* ItemStack pennyStack = new ItemStack(ItemRegistry.PENNY_ITEM, 0);
        ItemStack pennyStack1 = new ItemStack(ItemRegistry.PENNY_ITEM, 0);
        for(int i = 0; i <= amount; i+= 0.1f) {
            if(pennyStack.getCount() < 64) {
                pennyStack.increment(1);
            } else pennyStack1.increment(1);
        }

     /*   pennyStack.decrement(1);
        pennyStack1.decrement(1);

        player.giveItemStack(pennyStack);
        if(pennyStack1.getCount() > 0) player.giveItemStack(pennyStack1);
        Fabricmullah.decrBalance(player, amount);*/
    }

    private void giveAbove1(float amount, PlayerEntity player) {
        for (float i = amount; i > 0; i--) {
            if(i > 1f) {

            } else {
                giveBelow1(i, player);
                break;
            }
        }
    }
}

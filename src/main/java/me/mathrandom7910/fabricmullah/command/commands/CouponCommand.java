package me.mathrandom7910.fabricmullah.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import me.mathrandom7910.fabricmullah.registry.registries.ItemRegistry;
import me.mathrandom7910.fabricmullah.utils.WorldUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collections;

public class CouponCommand implements ICommandable {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("coupon")
                .then(CommandManager.argument("name", StringArgumentType.greedyString())
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            ItemStack stack = player.getMainHandStack();
                            if(stack.getItem() != Items.PAPER) {
                                player.sendMessage(Text.of("No paper in main hand!"), false);
                                return 1;
                            }


                            int amt = stack.getCount();
                            stack.setCount(0);

                            ItemStack stack1 = new ItemStack(ItemRegistry.COUPON_ITEM);

                            String name = player.getName().asString();
                            stack1.setCustomName(Text.of(name + (name.toLowerCase().endsWith("s") ? "'" : "'s") + " " + context.getArgument("name", String.class) + " coupon"));
                            //stack1.getNbt().putString("coupon", player.getUuidAsString());

                            for(int i = 0; i < amt; i++) {
                                WorldUtils.spawnItemEntity(stack1, player);
                            }
                            return 0;
                        })));
    }
}

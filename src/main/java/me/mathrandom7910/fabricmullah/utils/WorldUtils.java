package me.mathrandom7910.fabricmullah.utils;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WorldUtils {
    public static void spawnItemEntity(ItemStack stack, PlayerEntity player) {
        player.getEntityWorld().spawnEntity(new ItemEntity(player.world, player.getX(), player.getY(), player.getZ(), stack));

    }

    public static void spawnItemEntity(Item item, PlayerEntity player) {
        spawnItemEntity(new ItemStack(item), player);
    }
}

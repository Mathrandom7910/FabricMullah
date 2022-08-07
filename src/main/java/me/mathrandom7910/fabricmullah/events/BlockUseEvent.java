package me.mathrandom7910.fabricmullah.events;

import me.mathrandom7910.fabricmullah.registry.registries.ItemRegistry;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import java.util.Random;

public class BlockUseEvent implements UseBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();
        if(block == Blocks.ANVIL) {
            if(player.isSneaking()) return ActionResult.PASS;
            for(int i = 0; i < player.getInventory().size(); i++) {
                ItemStack stack = player.getInventory().getStack(i);
                if(stack.getItem() == ItemRegistry.COUPON_ITEM) {
                    player.setYaw(new Random().nextFloat() * 360);
                    player.setPitch(new Random().nextFloat() * 360);
                    player.sendMessage(Text.of("Cannot use anvil while coupons are in your inventory!"), false);
                    return ActionResult.FAIL;
                }
            }
        }
        return ActionResult.PASS;
    }
}

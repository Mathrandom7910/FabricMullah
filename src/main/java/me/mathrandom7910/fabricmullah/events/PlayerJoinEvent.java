package me.mathrandom7910.fabricmullah.events;

import me.mathrandom7910.ConfigHandler.Config;
import me.mathrandom7910.fabricmullah.Fabricmullah;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class PlayerJoinEvent implements ServerPlayConnectionEvents.Join {

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        PlayerEntity player = handler.getPlayer();
        String name = player.getGameProfile().getName();
        String uuid = player.getUuidAsString();

        Config playerConfig = Fabricmullah.HANDLER.addConfig(uuid);

        playerConfig.initVal("money", "0");

        Fabricmullah.NAME_TO_UUID.set(name, uuid);
        playerConfig.set("name", name);

        try {
            playerConfig.save();
            Fabricmullah.NAME_TO_UUID.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package me.mathrandom7910.fabricmullah.registry.registries;

import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.events.BlockUseEvent;
import me.mathrandom7910.fabricmullah.events.PlayerJoinEvent;
import me.mathrandom7910.fabricmullah.registry.IRegistry;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class EventRegistry implements IRegistry {

    @Override
    public void init() {
        Fabricmullah.LOG.info("Registering events");
        ServerPlayConnectionEvents.JOIN.register(new PlayerJoinEvent());
        UseBlockCallback.EVENT.register(new BlockUseEvent());
    }
}

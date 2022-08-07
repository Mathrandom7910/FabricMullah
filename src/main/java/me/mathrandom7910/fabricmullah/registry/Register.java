package me.mathrandom7910.fabricmullah.registry;

import me.mathrandom7910.fabricmullah.registry.registries.CommandsRegistry;
import me.mathrandom7910.fabricmullah.registry.registries.EventRegistry;
import me.mathrandom7910.fabricmullah.registry.registries.ItemRegistry;

public class Register {
    public static void register() {
        new ItemRegistry().init();
        new EventRegistry().init();
        new CommandsRegistry().init();
    }
}

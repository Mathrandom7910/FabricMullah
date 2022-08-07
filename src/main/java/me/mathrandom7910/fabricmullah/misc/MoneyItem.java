package me.mathrandom7910.fabricmullah.misc;

import net.minecraft.item.Item;

public class MoneyItem extends Item {
    private final int itemCost;
    public MoneyItem(Settings settings, int amt) {
        super(settings);
        itemCost = amt;
    }

    public int getItemCost() {
        return itemCost;
    }
}

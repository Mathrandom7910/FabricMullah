package me.mathrandom7910.fabricmullah.registry.registries;

import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.misc.MoneyItem;
import me.mathrandom7910.fabricmullah.registry.IRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry implements IRegistry {

    public static final MoneyItem PENNY_ITEM = registerMoneyItem("penny", new MoneyItem(new FabricItemSettings().group(ItemGroup.MISC), 1));
    public static final MoneyItem DOLLAR_ITEM = registerMoneyItem("dollar", initMoneyItem(PENNY_ITEM));
    public static final MoneyItem TEN_DOLLAR_ITEM = registerMoneyItem("ten_dollar", initMoneyItem(DOLLAR_ITEM));
    public static final MoneyItem HUNDRED_DOLLAR_ITEM = registerMoneyItem("hundred_dollar", initMoneyItem(TEN_DOLLAR_ITEM));
    public static final MoneyItem THOUSAND_DOLLAR_ITEM = registerMoneyItem("thousand_dollar", initMoneyItem(HUNDRED_DOLLAR_ITEM));

    public static final MoneyItem[] MONEY_ITEMS = new MoneyItem[]{PENNY_ITEM, DOLLAR_ITEM, TEN_DOLLAR_ITEM, HUNDRED_DOLLAR_ITEM, THOUSAND_DOLLAR_ITEM};

    public static final Item COUPON_ITEM = Registry.register(Registry.ITEM, new Identifier(Fabricmullah.ID, "coupon"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static MoneyItem registerMoneyItem(String name, Item item) {

        return (MoneyItem) Registry.register(Registry.ITEM, new Identifier(Fabricmullah.ID, name), item);
    }

    private static MoneyItem initMoneyItem(Item buildOn) {
        return new MoneyItem(new FabricItemSettings().group(ItemGroup.MISC), ((MoneyItem) buildOn).getItemCost() * 9);
    }

    @Override
    public void init() {
        Fabricmullah.LOG.info("Initializing items...");
    }
}

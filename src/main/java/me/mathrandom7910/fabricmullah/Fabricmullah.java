package me.mathrandom7910.fabricmullah;

import me.mathrandom7910.ConfigHandler.Config;
import me.mathrandom7910.ConfigHandler.ConfigHandler;
import me.mathrandom7910.fabricmullah.misc.MoneyItem;
import me.mathrandom7910.fabricmullah.registry.Register;
import me.mathrandom7910.fabricmullah.registry.registries.CommandsRegistry;
import me.mathrandom7910.fabricmullah.registry.registries.EventRegistry;
import me.mathrandom7910.fabricmullah.registry.registries.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.MinecraftVersion;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Fabricmullah implements ModInitializer {
    public static final String ID = "fabricmullah";
    public static final Logger LOG = LoggerFactory.getLogger(ID);
    public static final ConfigHandler HANDLER = new ConfigHandler("./mathrandom7910/fabricmullah");
    private static Fabricmullah INSTANCE;
    public static final Config NAME_TO_UUID = HANDLER.addConfig("name_to_uuid");
    public static final Config TOP_BAL = HANDLER.addConfig("top_bal");
    private static final int MAX_TOP = 5;


    @Override
    public void onInitialize() {
        Register.register();
    }

    public static int getBalance(PlayerEntity player) {
        return Integer.parseInt(HANDLER.getConfig(player.getUuidAsString()).get("money"));
    }

    public static void setBalance(PlayerEntity player, int balance) {
        Config config = HANDLER.getConfig(player.getUuidAsString());
        config.set("money", String.valueOf(balance));
        try {
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void incrBalance(PlayerEntity player, int amount) {
        setBalance(player, getBalance(player) + amount);
    }

    public static void decrBalance(PlayerEntity player, int amount) {
        incrBalance(player, -amount);
    }

    public static void decrBalance(PlayerEntity player, MoneyItem item) {
        decrBalance(player, item.getItemCost());
    }

    private static int getMoneyByName(String name) {
        Config config = HANDLER.getConfig(NAME_TO_UUID.get(name));
        if(config == null) return -1;
        return Integer.parseInt(config.get("money"));
    }

    public static void updateTop(PlayerEntity player) {
       /* try {
        String players = TOP_BAL.get("players");
        String name = player.getGameProfile().getName();
        if(players == null) {
            TOP_BAL.set("players", String.join(";", name));
        } else {
            List<String> playerNames = new ArrayList<>();

            for(String name1 : players.split(";")) {
                if(name1.equals(name)) continue;
                playerNames.add(name1);
            }
            int bal = getBalance(player);


            for(int i = 0; i < playerNames.size(); i++) {
                if(getMoneyByName(playerNames.get(i)) < bal) {
                    if(i + 1 >= playerNames.size()) break;
                    playerNames.add(i + 1, name);
                    break;
                }
            }

            TOP_BAL.set("players", String.join(";", playerNames));
        }

            TOP_BAL.save();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

       /* try {
            for (int i = MAX_TOP; i > 0; i--) {
                String strI = String.valueOf(i);
                String name = TOP_BAL.get(strI);
                String playerName = player.getGameProfile().getName();

                int bal = getBalance(player);
                int indBal = getMoneyByName(name);
                LOG.info(bal + " -<>- " + indBal);

                if (i == 1) {
                    if (name == null || indBal < bal) {
                        for(int i1 = MAX_TOP; i1 > 1; i1--) {
                            String gotName = TOP_BAL.get(String.valueOf(i1 - 1));
                            if(gotName == null) continue;
                            TOP_BAL.set(String.valueOf(i1), gotName);
                        }
                        TOP_BAL.set("1", playerName);
                    }
                    break;
                }

                if (getMoneyByName(TOP_BAL.get(String.valueOf(i - 1))) > bal) {
                    if (name == null || bal > indBal) {
                        for(int i1 = MAX_TOP; i1 > i; i1--) {
                            String gotName = TOP_BAL.get(String.valueOf(i1 - 1));
                            if(gotName == null) continue;
                            TOP_BAL.set(String.valueOf(i1), gotName);
                        }
                        TOP_BAL.set(strI, playerName);
                    }
                    break;
                }
            }
            TOP_BAL.save();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}

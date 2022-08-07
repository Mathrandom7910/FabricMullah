package me.mathrandom7910.fabricmullah.registry.registries;

import me.mathrandom7910.fabricmullah.Fabricmullah;
import me.mathrandom7910.fabricmullah.command.ICommandable;
import me.mathrandom7910.fabricmullah.command.commands.CouponCommand;
import me.mathrandom7910.fabricmullah.command.commands.balance.BalanceCommand;
import me.mathrandom7910.fabricmullah.command.commands.PayCommand;
import me.mathrandom7910.fabricmullah.command.commands.DepositCommand;
import me.mathrandom7910.fabricmullah.command.commands.WithdrawCommand;
import me.mathrandom7910.fabricmullah.command.commands.balance.SearchBalCommand;
import me.mathrandom7910.fabricmullah.registry.IRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class CommandsRegistry implements IRegistry {

    @Override
    public void init() {
        Fabricmullah.LOG.info("Registering commands");
        CommandRegistrationCallback.EVENT.register(new DepositCommand()::register);
        CommandRegistrationCallback.EVENT.register(new WithdrawCommand()::register);
        CommandRegistrationCallback.EVENT.register(new BalanceCommand()::register);
        CommandRegistrationCallback.EVENT.register(new PayCommand()::register);
        CommandRegistrationCallback.EVENT.register(new SearchBalCommand()::register);
        CommandRegistrationCallback.EVENT.register(new CouponCommand()::register);
    }


    private void register(ICommandable commandable) {
        CommandRegistrationCallback.EVENT.register(commandable::register);
    }
}

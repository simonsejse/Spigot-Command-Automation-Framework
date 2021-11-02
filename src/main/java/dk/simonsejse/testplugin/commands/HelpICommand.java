package dk.simonsejse.testplugin.commands;

import dk.simonsejse.testplugin.exceptions.WrongCommandUsage;
import org.bukkit.entity.Player;

@ICommandImpl(cooldown = 10, cmdName = "help", usage = "/help", info = "Få hjælp")
public class HelpICommand implements ICommandPerformImpl{
    @Override
    public void performOnCommand(Player player, String[] args) throws WrongCommandUsage {
        player.sendMessage("Denne kommando virker også LOL!");
    }
}

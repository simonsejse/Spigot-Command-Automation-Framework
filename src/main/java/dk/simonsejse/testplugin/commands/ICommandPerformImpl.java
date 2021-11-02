package dk.simonsejse.testplugin.commands;

import dk.simonsejse.testplugin.exceptions.WrongCommandUsage;
import org.bukkit.entity.Player;

public interface ICommandPerformImpl {
    void performOnCommand(Player player, String[] args) throws WrongCommandUsage;
}

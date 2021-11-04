package dk.simonsejse.testplugin.commands;

import org.bukkit.entity.Player;

public interface ISubCommand {
    String name();
    void performArgument(Player player, String[] args);
}

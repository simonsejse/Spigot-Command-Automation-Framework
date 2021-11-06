package dk.simonsejse.automatecommands.commands;

import org.bukkit.entity.Player;

public interface ISubCommandPerformImpl {
    void performArgument(Player player, String[] args);
}

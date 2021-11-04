package dk.simonsejse.testplugin.commands.gamemode;

import dk.simonsejse.testplugin.commands.ISubCommand;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SurvivalArgument implements ISubCommand {
    @Override
    public String name() {
        return "survival";
    }

    @Override
    public void performArgument(Player player, String[] args) {
        player.setGameMode(GameMode.SURVIVAL);
    }
}

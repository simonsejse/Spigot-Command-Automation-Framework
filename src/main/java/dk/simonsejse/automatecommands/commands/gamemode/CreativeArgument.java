package dk.simonsejse.automatecommands.commands.gamemode;

import dk.simonsejse.automatecommands.commands.ISubCommand;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CreativeArgument implements ISubCommand {
    @Override
    public String name() {
        return "creative";
    }

    @Override
    public void performArgument(Player player, String[] args) {
        player.setGameMode(GameMode.CREATIVE);
    }
}

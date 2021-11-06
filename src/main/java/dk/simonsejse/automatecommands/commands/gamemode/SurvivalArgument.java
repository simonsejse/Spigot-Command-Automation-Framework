package dk.simonsejse.automatecommands.commands.gamemode;

import dk.simonsejse.automatecommands.commands.ISubCommandImpl;
import dk.simonsejse.automatecommands.commands.ISubCommandPerformImpl;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@ISubCommandImpl(
        superCommand = GamemodeAbstractCommand.class,
        parameterName = "survival"
)
public class SurvivalArgument implements ISubCommandPerformImpl {
    @Override
    public void performArgument(Player player, String[] args) {
        player.setGameMode(GameMode.SURVIVAL);
    }
}

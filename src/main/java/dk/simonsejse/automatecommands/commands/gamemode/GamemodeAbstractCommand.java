package dk.simonsejse.automatecommands.commands.gamemode;

import dk.simonsejse.automatecommands.commands.ICommandImpl;
import dk.simonsejse.automatecommands.commands.AbstractCommandPerformImpl;
import dk.simonsejse.automatecommands.exceptions.WrongCommandUsage;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@ICommandImpl(
        cmdName = "gamemode",
        permission = "bukkit.commands.gamemode",
        usage = "/gamemode",
        info = "Kommandoen bruges til at skifte gamemode",
        cooldown = 5
)
public class GamemodeAbstractCommand extends AbstractCommandPerformImpl {

    @Override
    public void performPostExecutionWhenArgumentsNotFound(Player player, String[] args) throws WrongCommandUsage {
        GameMode postGameMode = player.getGameMode() == GameMode.CREATIVE ? GameMode.SURVIVAL : GameMode.CREATIVE;
        player.setGameMode(postGameMode);
    }
}

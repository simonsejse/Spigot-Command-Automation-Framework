package dk.simonsejse.automatecommands.commands.gamemode;

import dk.simonsejse.automatecommands.commands.ICommandImpl;
import dk.simonsejse.automatecommands.commands.ICommandPerformImpl;
import dk.simonsejse.automatecommands.commands.ISubCommand;
import dk.simonsejse.automatecommands.exceptions.WrongCommandUsage;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

@ICommandImpl(
        cmdName = "gamemode",
        permission = "bukkit.commands.gamemode",
        usage = "/gamemode",
        info = "Kommandoen bruges til at skifte gamemode",
        cooldown = 5
)
public class GamemodeCommand implements ICommandPerformImpl {


    @Nullable
    @Override
    public List<ISubCommand> subcommands() {
        return Arrays.asList(
                new CreativeArgument(),
                new SurvivalArgument()
        );
    }

    @Override
    public void performPostExecutionWhenArgumentsNotFound(Player player, String[] args) throws WrongCommandUsage {
        GameMode postGameMode = player.getGameMode() == GameMode.CREATIVE ? GameMode.SURVIVAL : GameMode.CREATIVE;
        player.setGameMode(postGameMode);
    }
}

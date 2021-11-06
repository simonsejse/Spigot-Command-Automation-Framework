package dk.simonsejse.automatecommands.commands.gamemode;

import dk.simonsejse.automatecommands.commands.ISubCommandImpl;
import dk.simonsejse.automatecommands.commands.ISubCommandPerformImpl;
import org.bukkit.entity.Player;

@ISubCommandImpl(
        superCommand = GamemodeAbstractCommand.class,
        parameterName = "creative"
)
public class CreativeArgument implements ISubCommandPerformImpl {
    @Override
    public void performArgument(Player player, String[] args) {

    }
}

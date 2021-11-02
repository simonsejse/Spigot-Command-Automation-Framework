package dk.simonsejse.testplugin.commands;

import dk.simonsejse.testplugin.exceptions.WrongCommandUsage;
import org.bukkit.entity.Player;

@ICommandImpl(
        cooldown = 7,
        cmdName = "test",
        info = "A command for my testing",
        usage = "/test",
        permission="command.bukkit.test"
)
public class TestICommand implements ICommandPerformImpl {

    @Override
    public void performOnCommand(Player player, String[] args) throws WrongCommandUsage {
        player.sendMessage("Kommandoen virker!");


    }

}

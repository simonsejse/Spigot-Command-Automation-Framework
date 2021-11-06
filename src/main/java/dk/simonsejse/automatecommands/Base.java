package dk.simonsejse.automatecommands;

import dk.simonsejse.automatecommands.commands.CommandManager;
import dk.simonsejse.automatecommands.cooldown.CooldownManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Base extends JavaPlugin {

    public CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        this.cooldownManager = new CooldownManager(this);

        final CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

package dk.simonsejse.testplugin;

import dk.simonsejse.testplugin.commands.CommandManager;
import dk.simonsejse.testplugin.cooldown.CooldownManager;
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

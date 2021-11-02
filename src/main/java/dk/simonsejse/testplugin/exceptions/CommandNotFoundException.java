package dk.simonsejse.testplugin.exceptions;

import org.bukkit.command.CommandExecutor;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException(String message){
        super(message);
    }
}

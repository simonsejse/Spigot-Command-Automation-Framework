package dk.simonsejse.testplugin.exceptions;

import org.bukkit.command.Command;

public class CommandCooldownException extends RuntimeException{
    public CommandCooldownException(String error){
        super(error);
    }
}

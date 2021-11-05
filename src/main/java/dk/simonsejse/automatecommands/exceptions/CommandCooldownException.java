package dk.simonsejse.automatecommands.exceptions;

public class CommandCooldownException extends RuntimeException{
    public CommandCooldownException(String error){
        super(error);
    }
}

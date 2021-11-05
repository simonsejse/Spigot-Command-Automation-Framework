package dk.simonsejse.automatecommands.exceptions;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException(String message){
        super(message);
    }
}

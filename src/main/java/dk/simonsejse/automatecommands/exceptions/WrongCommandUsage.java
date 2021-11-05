package dk.simonsejse.automatecommands.exceptions;

public class WrongCommandUsage extends RuntimeException{
    public WrongCommandUsage(String message) {
        super(message);
    }
}

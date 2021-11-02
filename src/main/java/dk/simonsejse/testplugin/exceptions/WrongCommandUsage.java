package dk.simonsejse.testplugin.exceptions;

public class WrongCommandUsage extends RuntimeException{
    public WrongCommandUsage(String message) {
        super(message);
    }
}
